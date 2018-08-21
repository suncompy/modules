package com.lebaoxun.modules.fastfood.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderChildsDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service("foodOrderService")
public class FoodOrderServiceImpl extends ServiceImpl<FoodOrderDao, FoodOrderEntity> implements FoodOrderService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private FoodMachineAisleDao foodMachineAisleDao;
	
	@Resource
	private FoodOrderChildsDao foodOrderChildsDao;
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderEntity> page = this.selectPage(
                new Query<FoodOrderEntity>(params).getPage(),
                new EntityWrapper<FoodOrderEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<FoodOrderEntity> createOrderByShoppingCart(Long userId,
			List<FoodShoppingCartEntity> carts) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String format1(Integer value, int minLength) {
		StringBuffer st = new StringBuffer(value.toString());
		if (st.length() < minLength) {
			int len = minLength - st.length();
			for (int i = 0; i < len; i++) {
				st.insert(0, "0");
			}
		}
		return st.toString();
	}
	
	private synchronized String getOrder(Date now){
		String date = DateFormatUtils.format(now, "yyyyMMdd");
		String key = "mall:orderNo:" + date;
		Integer inr = (Integer) redisTemplate.opsForValue().get(key);
		if (inr == null) {
			inr = RandomUtils.nextInt(10000);
			redisTemplate.opsForValue().set(key, inr, 25l,
					TimeUnit.valueOf("HOURS"));
		} else {
			inr = redisTemplate.opsForValue().increment(key, 1).intValue();
		}
		String orderNo = "FD" + date + format1(inr, 5);
		return orderNo;
	}
	
	@Override
	public FoodOrderEntity calCheckTotalFee(FoodOrderEntity order) {
		// TODO Auto-generated method stub
		if(order == null || order.getChilds() == null || order.getChilds().isEmpty()){
			return order;
		}
		BigDecimal totalFee = new BigDecimal("0.00");
		Integer buyNumber = 0;
		for(FoodOrderChildsEntity orderChild : order.getChilds()){
			Map<String,Object> aisle = foodMachineAisleDao.findProductByAisle(orderChild.getMacId(),
					orderChild.getProductId(), null, orderChild.getAisleId());
			if(aisle == null){
				throw new I18nMessageException("60001","对不起，产品不存在或已过期");
			}
			if(order.getBuyNumber() == null || order.getBuyNumber() <= 0){
				throw new I18nMessageException("60004","商品数量有误");
			}
			Integer macId = (Integer)aisle.get("macId"),
					productId = (Integer)aisle.get("productId"),
							productCatId = (Integer)aisle.get("productCatId");
			
			buyNumber += buyNumber;
			String productName = (String)aisle.get("productName");
			Integer stock = (Integer)aisle.get("stock");
			if(stock == null || stock < order.getBuyNumber()){
				throw new I18nMessageException("60005","‘"+productName+"’产品库存不足");
			}
			BigDecimal price = (BigDecimal)aisle.get("price");
			BigDecimal fee = price.multiply(new BigDecimal(orderChild.getBuyNumber()));
			orderChild.setCostPrice(fee);
			orderChild.setMacId(macId);
			orderChild.setProductId(productId);
			orderChild.setProductAmount(price);
			orderChild.setProductCatId(productCatId);
			orderChild.setProductId(productId);
			orderChild.setProductName(productName);
			
			order.setMacId(macId);
			totalFee.add(fee);
			logger.debug("price={},buyNumber={},fee={}",price,order.getBuyNumber(),fee);
		}
		order.setBuyNumber(buyNumber);
		order.setOrderAmount(totalFee);
		return order;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String createOrder(Long userId,
			FoodOrderEntity order) {
		
		Date now = new Date();
		
		this.calCheckTotalFee(order);
		
		int count = this.baseMapper
				.selectCount(new EntityWrapper<FoodOrderEntity>().eq("user_id",
						userId).eq("order_status", 0));
		
		redisTemplate.opsForValue().get("order:config:timeout");
		Integer nopayMaxCount = (Integer)redisTemplate.opsForValue().get("order:config:nopay_max_count");
		if(nopayMaxCount != null && count > nopayMaxCount){
			throw new I18nMessageException("60006","订单创建失败，您有" + count
					+ "个尚未支付订单，请立即处理!");
		}
		
		String orderNo = getOrder(now);
		// TODO Auto-generated method stub

		order.setCreateTime(now);
		order.setUserId(userId);
		order.setOrderStatus(0);
		order.setOrderNo(orderNo);

		this.baseMapper.insert(order);
		
		List<FoodOrderChildsEntity> childs = order.getChilds();
		for(FoodOrderChildsEntity child : childs){
			child.setOrderId(order.getId());
			FoodMachineAisleEntity aisle = new FoodMachineAisleEntity();
			aisle.setId(child.getAisleId());
			aisle = foodMachineAisleDao.selectOne(aisle);
			aisle.setStock(aisle.getStock() - child.getBuyNumber());
			foodMachineAisleDao.updateById(aisle);
			foodOrderChildsDao.insert(child);
		}
		
		return orderNo;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyQrCodeByOrderNo(String orderNo, String qrCode) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = this.selectOne(new EntityWrapper<FoodOrderEntity>().eq("order_no",orderNo));
		if(order == null){
			throw new I18nMessageException("60007","订单不存在");
		}
		order.setQrCode(qrCode);
		this.baseMapper.updateById(order);
	}

	@Override
	public Map<String, Object> getSweeptCodeOrderInfo(String orderId) {
		return this.baseMapper.getSweeptCodeOrderInfo(orderId);
	}


}
