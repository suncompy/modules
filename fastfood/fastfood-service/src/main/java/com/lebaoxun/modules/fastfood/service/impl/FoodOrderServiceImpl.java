package com.lebaoxun.modules.fastfood.service.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderChildsDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityFirstOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;


@Service("foodOrderService")
public class FoodOrderServiceImpl extends ServiceImpl<FoodOrderDao, FoodOrderEntity> implements FoodOrderService {
	private final static String ORDER_QUEUE_KEY="take_food:order_queue";
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Resource
	private FoodMachineAisleDao foodMachineAisleDao;
	
	@Resource
	private FoodOrderChildsDao foodOrderChildsDao;
	
	@Resource
	private OperateActivityFirstOrderDao operateActivityFirstOrderDao;
	
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
	public FoodOrderEntity calCheckTotalFee(Boolean isFirstOrder,FoodOrderEntity order) {
		// TODO Auto-generated method stub
		if(order == null || order.getChilds() == null || order.getChilds().isEmpty()){
			return order;
		}
		OperateActivityFirstOrderEntity firstOrderActivity = operateActivityFirstOrderDao.findUnderwayActivity();
		BigDecimal totalFee = new BigDecimal("0.00");
		Integer buyNumber = 0;
		boolean isProductActivity = false;
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
			
			if(isFirstOrder != null && isFirstOrder == true){//首单
				if("firstOrder".equals(aisle.get("activity")) && firstOrderActivity != null){//含有首单活动
					fee = fee.subtract(firstOrderActivity.getAmount());
					orderChild.setActivity("firstOrder");
					isProductActivity = true;
				}
			}else{
				if("keepDiscount".equals(aisle.get("activity")) && firstOrderActivity != null){//含有首单活动
					fee = fee.subtract(firstOrderActivity.getAmount());
					orderChild.setActivity("firstOrder");
					isProductActivity = true;
				}
			}
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
	public FoodOrderEntity createOrder(Long userId,
			Boolean isFirstOrder,
			FoodOrderEntity order) {
		
		Date now = new Date();
		
		this.calCheckTotalFee(isFirstOrder,order);
		
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
		
		Map<String,String> message = new HashMap<String,String>();
		message.put("orderNo", orderNo);
		rabbitmqSender.sendContractDirect("order.qrcode.create.queue",
				new Gson().toJson(message));
		return order;
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
	@Override
	public ResponseMessage pushOrder(Long orderId){
		HashOperations<String,Long, FoodOrderEntity> operations = redisTemplate.opsForHash();
		//查询订单是否已经加入队列
		try {
			FoodOrderEntity foodOrderCache= operations.get(ORDER_QUEUE_KEY,orderId);
			if (foodOrderCache!=null||foodOrderCache.getId()>0){
				return ResponseMessage.ok(foodOrderCache);
			}
			//查询订单，验证信息
			FoodOrderEntity foodOrderEntity=selectById(orderId);
			if (foodOrderEntity==null||foodOrderEntity.getId()==0){
				return ResponseMessage.error("00002","数据异常");
			}
			//订单状校验，只有已支付的订单才能有些操作
			if (foodOrderEntity.getOrderStatus()!=1){
				return ResponseMessage.error("00003","该订单未支付，请先完成支付！");
			}
			redisTemplate.setEnableTransactionSupport(true);
			redisTemplate.multi();
			//当前排队数
			Long num=operations.size(ORDER_QUEUE_KEY);
			operations.put(ORDER_QUEUE_KEY,orderId,foodOrderEntity);
			List<Object> list=redisTemplate.exec();
			redisTemplate.setEnableTransactionSupport(false);
			list.forEach(e->System.out.print(e));

			Map<String,Object>result= Maps.newHashMap();
			result.put("orderId",orderId);
			result.put("orderNo",foodOrderEntity.getOrderNo());
			result.put("currentNum",num);
			return ResponseMessage.ok(result);
		}catch (Error e){
			e.printStackTrace();
			return ResponseMessage.error("00005",e.getMessage());
		}
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ResponseMessage takeFoodCallback(String orderId){
		//更新订单状
		FoodOrderEntity foodOrder=new FoodOrderEntity();
		foodOrder.setId(Long.parseLong(orderId));
		//已取餐
		foodOrder.setOrderStatus(2);
		EntityWrapper<FoodOrderEntity> foodOrderWrapper=new EntityWrapper<FoodOrderEntity>();
		foodOrderWrapper.eq("id",orderId);
		//必需要已支付订单才能操作
		foodOrderWrapper.eq("order_status",1);
		if (update(foodOrder,foodOrderWrapper)){
			//从队列中踢除
			HashOperations<String,Long, FoodOrderEntity> operations = redisTemplate.opsForHash();
			long ret=operations.delete(ORDER_QUEUE_KEY,orderId);
			if (ret==1){
				foodOrder=selectById(orderId);
				Map<String,Object>resultData= Maps.newHashMap();
				resultData.put("orderId",orderId);
				resultData.put("orderNo",foodOrder.getOrderNo());
				return ResponseMessage.ok(resultData);
			}
		}
		return ResponseMessage.error("0004","取餐失败!");
	}

}
