package com.lebaoxun.modules.fastfood.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.modules.fastfood.dao.FoodOrderBackDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderBackEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderBackService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;


@Service("foodOrderBackService")
public class FoodOrderBackServiceImpl extends ServiceImpl<FoodOrderBackDao, FoodOrderBackEntity> implements FoodOrderBackService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private FoodOrderDao foodOrderDao;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String orderNo = (String)params.get("orderNo"),
    			serviceNo = (String)params.get("serviceNo"),
    				status = (String)params.get("status"),
    					createBy = (String)params.get("createBy"),
    						feedbackTime = (String)params.get("feedbackTime");
        Page<FoodOrderBackEntity> page = this.selectPage(
                new Query<FoodOrderBackEntity>(params).getPage(),
                new EntityWrapper<FoodOrderBackEntity>()
                .eq(StringUtils.isNotBlank(feedbackTime), "DATE_FORMAT(feedback_time,'%Y-%m-%d')", feedbackTime)
                .eq(StringUtils.isNotBlank(orderNo), "order_no", orderNo)
                .eq(StringUtils.isNotBlank(serviceNo), "service_no", serviceNo)
                .eq(StringUtils.isNotBlank(createBy) && StringUtils.isInteger(createBy), "create_by", createBy)
                .eq(StringUtils.isNotBlank(status) && StringUtils.isInteger(status), "status", status)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean insert(FoodOrderBackEntity entity) {
    	// TODO Auto-generated method stub
    	int count = this.selectCount(new EntityWrapper<FoodOrderBackEntity>().eq("order_no", entity.getOrderNo()));
    	if(count > 0){
    		throw new I18nMessageException("60010","此订单已有反馈记录");
    	}
    	
    	FoodOrderEntity order = new FoodOrderEntity();
    	order.setId(null);
    	order.setOrderNo(entity.getOrderNo());
    	order.setOrderStatus(1);
    	order = foodOrderDao.selectOne(order);
    	if(order == null){
    		throw new I18nMessageException("60007","订单不存在");
    	}
    	if(order.getUserId() == null){
    		throw new I18nMessageException("60012","非用户发起订单无法退款");
    	}
    	entity.setPayAmount(order.getPayAmount());
    	entity.setStatus(0);
    	entity.setUserId(order.getUserId());
    	return super.insert(entity);
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public List<FoodOrderBackEntity> checkOrderBack(Long checkId, String[] orderNos,
    		String checkRemark, Integer status) {
    	List<FoodOrderBackEntity> orders = new ArrayList<FoodOrderBackEntity>();
    	for(String orderNo: orderNos){
    		// TODO Auto-generated method stub
    		FoodOrderBackEntity orderBack = this.selectOne(new EntityWrapper<FoodOrderBackEntity>().eq("order_no", orderNo).eq("status", 0));
    		if(orderBack == null){
    			throw new I18nMessageException("60011","反馈记录不存在或已审核");
    		}
    		orderBack.setCheckId(checkId);
    		orderBack.setCheckRemark(checkRemark);
    		orderBack.setCheckTime(new Date());
    		orderBack.setStatus(status);//1=已确认，-1=已拒绝
    		orders.add(orderBack);
    		this.updateById(orderBack);
    		
    		if(status == 1){
    			FoodOrderEntity order = new FoodOrderEntity();
    			order.setId(null);
    			order.setOrderStatus(-1);
    			foodOrderDao.update(order, new EntityWrapper<FoodOrderEntity>().eq("order_no", orderNo));
    		}
    	}
    	
    	if(status == 1){
    		for(FoodOrderBackEntity orderBack: orders){
    			String logType = "ORDER_REFUND";
    			Map<String,String> pmessage = new HashMap<String,String>();
    			
    			pmessage.put("out_trade_no", orderBack.getOrderNo());
    			pmessage.put("recharge_fee", orderBack.getPayAmount().toString());
    			pmessage.put("log_type", logType);
    			pmessage.put("descr", "订单退款");
    			pmessage.put("adjunctInfo", orderBack.getOrderNo());
    			pmessage.put("user_id", orderBack.getUserId()+"");
    			pmessage.put("buy_time", new Date().getTime()+"");
    			pmessage.put("token", MD5.md5(logType.toString()+"_"+orderBack.getOrderNo()));
    			
    			logger.debug("订单退款|pmessage={}",new Gson().toJson(pmessage));
    			rabbitmqSender.sendContractDirect("account.balance.queue.award",
    					new Gson().toJson(pmessage));
    		}
    	}
    	return orders;
    }
}
