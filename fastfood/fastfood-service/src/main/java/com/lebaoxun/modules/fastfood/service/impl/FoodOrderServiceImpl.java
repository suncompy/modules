package com.lebaoxun.modules.fastfood.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.account.service.IUserService;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.dao.FoodMachineDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderChildsDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.dao.FoodShoppingCartDao;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityFirstOrderDao;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityKeepDiscountDao;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityPayCashBackDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.entity.TakeFoodCodeEntity;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityPayCashBackEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.modules.operate.dao.OperateCouponRecordDao;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.pay.service.IWxPayService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import com.lebaoxun.soa.core.redis.IRedisHash;

@Service("foodOrderService")
public class FoodOrderServiceImpl extends
		ServiceImpl<FoodOrderDao, FoodOrderEntity> implements FoodOrderService {
	private final static String ORDER_QUEUE_KEY = "take_food:order_queue";
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IRabbitmqSender rabbitmqSender;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Resource
	private IRedisHash redisHash;

	@Resource
	private FoodMachineAisleDao foodMachineAisleDao;

	@Resource
	private FoodMachineDao foodMachineDao;

	@Resource
	private FoodOrderChildsDao foodOrderChildsDao;

	@Resource
	private OperateActivityFirstOrderDao operateActivityFirstOrderDao;

	@Resource
	private OperateActivityKeepDiscountDao operateActivityKeepDiscountDao;
	
	@Resource
	private OperateActivityPayCashBackDao operateActivityPayCashBackDao;

	@Resource
	private OperateCouponRecordDao operateCouponRecordDao;

	@Resource
	private FoodShoppingCartDao foodShoppingCartDao;

	@Resource
	private IWxPayService wxPayService;
	
	@Resource
	private IUserService userService;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<FoodOrderEntity> page = this.selectPage(
				new Query<FoodOrderEntity>(params).getPage(),
				new EntityWrapper<FoodOrderEntity>());

		return new PageUtils(page);
	}

	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ResponseMessage createOrderByMac(String spbill_create_ip, String payGroup,FoodOrderEntity order) {
    	// TODO Auto-generated method stub
    	
    	Date now = new Date();
    	
		this.calCheckTotalFee(null,null,order, true, false);
		
		String orderNo = getOrder(now);
		// TODO Auto-generated method stub

		order.setCreateTime(now);
		order.setTakeFoodTime(now);
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
		order.setPayType(1);//1=在线支付
    	this.baseMapper.updateById(order);
		
    	BigDecimal payAmount = order.getPayAmount();
    	
    	return wxPayService.qrcodePayment(spbill_create_ip, orderNo, "购买餐品", payAmount.setScale(2, BigDecimal.ROUND_DOWN)
				.multiply(new BigDecimal("100")).intValue(), "", payGroup, order.getMacId().longValue(), null, "shopping_for_mac");
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ResponseMessage wxAppPayForOrder(Long userId, BigDecimal dis,
			String spbill_create_ip, String payGroup, String openid,
			String orderNo) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = this
				.selectOne(new EntityWrapper<FoodOrderEntity>()
						.eq("user_id", userId).eq("order_no", orderNo)
						.eq("order_status", 0));
		if (order == null) {
			throw new I18nMessageException("60009", "订单不存在或已支付");
		}
		List<FoodOrderChildsEntity> childs = foodOrderChildsDao
				.selectList(new EntityWrapper<FoodOrderChildsEntity>().eq(
						"user_id", userId).eq("order_id", order.getId()));
		order.setChilds(childs);

		this.calCheckTotalFee(userId, dis, order, false, true);

		order.setPayType(1);// 1=在线支付
		this.baseMapper.updateById(order);

		for (FoodOrderChildsEntity child : childs) {
			foodOrderChildsDao.updateById(child);
		}

		BigDecimal payAmount = order.getPayAmount();
		return wxPayService.payment(
				spbill_create_ip,
				orderNo,
				"购买餐品",
				payAmount.setScale(2, BigDecimal.ROUND_DOWN)
						.multiply(new BigDecimal("100")).intValue(), "",
				payGroup, openid, userId, null, "shopping");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public FoodOrderEntity createOrderByShoppingCart(Long userId,
			BigDecimal dis, List<FoodShoppingCartEntity> carts) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = new FoodOrderEntity();
		List<FoodOrderChildsEntity> childs = new ArrayList<FoodOrderChildsEntity>();
		Integer macId = null;
		for (FoodShoppingCartEntity cart : carts) {
			FoodShoppingCartEntity query = new FoodShoppingCartEntity();
			query.setCartId(cart.getCartId());
			query.setUserId(userId);
			FoodShoppingCartEntity foodCart = foodShoppingCartDao
					.selectOne(query);
			if (macId != null && !foodCart.getMacId().equals(macId)) {
				throw new I18nMessageException("60008", "商品不在同一个机器中，无法下单");
			}
			Integer buyNumber = cart.getBuyNumber() == null ? foodCart
					.getBuyNumber() : cart.getBuyNumber();
			FoodOrderChildsEntity orderChild = new FoodOrderChildsEntity();
			orderChild.setAisleId(cart.getAisleId());
			orderChild.setBuyNumber(buyNumber);
			orderChild.setMacId(macId);
			orderChild.setProductId(foodCart.getProductId());
			childs.add(orderChild);
			macId = foodCart.getMacId();
		}
		order.setMacId(macId);
		order = createOrder(userId, dis, order);

		for (FoodShoppingCartEntity cart : carts) {
			foodShoppingCartDao.deleteById(cart.getCartId());
		}
		return order;
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

	private synchronized String getOrder(Date now) {
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
	public ResponseMessage balancePayForOrder(Long userId, BigDecimal dis,
			String orderNo) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = this
				.selectOne(new EntityWrapper<FoodOrderEntity>()
						.eq("user_id", userId).eq("order_no", orderNo)
						.eq("order_status", 0));
		if (order == null) {
			throw new I18nMessageException("60009", "订单不存在或已支付");
		}
		
		List<FoodOrderChildsEntity> childs = foodOrderChildsDao
				.selectList(new EntityWrapper<FoodOrderChildsEntity>().eq(
						"user_id", userId).eq("order_id", order.getId()));
		order.setChilds(childs);

		this.calCheckTotalFee(userId, dis, order, false, true);

		order.setPayType(2);// 1=余额支付
		this.baseMapper.updateById(order);

		for (FoodOrderChildsEntity child : childs) {
			foodOrderChildsDao.updateById(child);
		}

		BigDecimal payAmount = order.getPayAmount();
		return userService.balancePay(userId, payAmount, "", orderNo, "支付餐品订单");
	}

	@Override
	public FoodOrderEntity calCheckTotalFee(Long userId, BigDecimal dis,
			FoodOrderEntity order,boolean isCheckStock,boolean isCheckActivity) {
		// TODO Auto-generated method stub
		if (order == null || order.getChilds() == null
				|| order.getChilds().isEmpty()) {
			return order;
		}

		int noPay = this.selectCount(new EntityWrapper<FoodOrderEntity>().eq(
				"user_id", userId).gt("order_status", 0));
		boolean isFirstOrder = noPay > 0 ? true : false;

		OperateActivityFirstOrderEntity firstOrderActivity = operateActivityFirstOrderDao
				.findUnderwayActivity();
		OperateActivityKeepDiscountEntity keepDiscountActivity = operateActivityKeepDiscountDao
				.findUnderwayActivity();
		OperateActivityPayCashBackEntity payCashBackEntity = operateActivityPayCashBackDao.findUnderwayActivity();

		BigDecimal totalFee = new BigDecimal("0.00"), payAmount = new BigDecimal(
				"0.00");
		Integer buyNumber = 0;
		boolean isCalProductActivity = false;// 是否产品已有活动
		boolean isCanUseCoupon = true;// 是否能使用优惠券

		String firstOrderActivityType = "1";

		for (FoodOrderChildsEntity orderChild : order.getChilds()) {
			Map<String, Object> aisle = foodMachineAisleDao.findProductByAisle(
					orderChild.getMacId(), orderChild.getProductId(), null,
					orderChild.getAisleId());
			if (aisle == null) {
				throw new I18nMessageException("60001", "对不起，产品不存在或已过期");
			}
			if (orderChild.getBuyNumber() == null
					|| orderChild.getBuyNumber() <= 0) {
				throw new I18nMessageException("60004", "商品数量有误");
			}
			Integer macId = (Integer) aisle.get("macId"), productId = (Integer) aisle
					.get("productId"), productCatId = (Integer) aisle
					.get("productCatId");

			String productName = (String) aisle.get("productName");
			Integer stock = (Integer) aisle.get("stock");
			if (isCheckStock && (stock == null || stock < orderChild.getBuyNumber())) {
				throw new I18nMessageException("60005", "‘" + productName
						+ "’产品库存不足");
			}
			buyNumber += orderChild.getBuyNumber();
			BigDecimal price = (BigDecimal) aisle.get("price");
			BigDecimal fee = price.multiply(new BigDecimal(orderChild
					.getBuyNumber()));
			orderChild.setCostPrice(fee);
			orderChild.setMacId(macId);
			orderChild.setProductAmount(fee);
			orderChild.setProductCatId(productCatId);
			orderChild.setProductId(productId);
			orderChild.setProductName(productName);
			orderChild.setPrice(price);
			orderChild.setStatus(0);
			orderChild.setActivity(null);
			orderChild.setActivityFee(null);
			totalFee = totalFee.add(fee);
			if (firstOrderActivityType.equals(aisle.get("activity"))
					&& firstOrderActivity != null) {// 含有首单活动
				if(isCheckActivity){
					if (isFirstOrder == true) {// 首单
						fee = fee.subtract(firstOrderActivity.getAmount());
						orderChild.setActivity(firstOrderActivityType);
						orderChild.setActivityFee(firstOrderActivity.getAmount());
						orderChild.setActivityId(firstOrderActivity.getId());
						isCalProductActivity = true;
						isCanUseCoupon = false;
					}
				}
			}
			
			if ("2".equals(aisle.get("activity"))
					&& keepDiscountActivity != null) {
				if(isCheckActivity){
					if(keepDiscountActivity.getJoinRestrict().compareTo(fee) >= 0){//满足参加活动状态
						Integer count = getActivityFor("2", keepDiscountActivity.getId(), macId, productId, userId,"2");
						if (count == 0) {//当天未参加
							Date start = keepDiscountActivity.getStartTime(),
									end = new Date();
							if(start.after(end)){
								logger.error("数据库时间与服务器时间不同步start={},end={}",start,end);
							}else{
								Calendar aCalendar = Calendar.getInstance();
						        aCalendar.setTime(start);
						        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
						        aCalendar.setTime(end);
						        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
						        int days = day2-day1;
						        
								BigDecimal initDis = keepDiscountActivity.getInitDis();
								BigDecimal keepDis = initDis.add(keepDiscountActivity.getProIncr().multiply(new BigDecimal(days))); 
								logger.debug("cal before keepDis={},fee={}",keepDis,fee);
								if(keepDis.compareTo(new BigDecimal("10")) >= 0){
									keepDis = new BigDecimal("10");
								}
								logger.debug("cal after keepDis={},fee={}",keepDis,fee);
								BigDecimal activityFee = fee.multiply(keepDis.divide(new BigDecimal(10)));
								fee = fee.subtract(activityFee);
								logger.debug("activity cal after fee={},activityFee={}",fee,activityFee);
								orderChild.setActivity("2");
								orderChild.setActivityFee(activityFee);
								orderChild.setActivityId(keepDiscountActivity.getId());
								isCalProductActivity = true;
								isCanUseCoupon = false;
							}
						}
					}
				}
			}
			
			if ("3".equals(aisle.get("activity"))
					&& payCashBackEntity != null) {
				if(isCheckActivity){
					Integer count = getActivityFor("3", payCashBackEntity.getId(), macId, productId, null, null);
					logger.debug("activity 3 count={},personTime={}",count,payCashBackEntity.getPersonTime());
					if(count < payCashBackEntity.getPersonTime()){//人数
						BigDecimal amount = payCashBackEntity.getAmount();
						BigDecimal activityFee = amount.subtract(amount.multiply(new BigDecimal(count)));
						logger.debug("activity cal after fee={}",fee);
						orderChild.setActivity("3");
						orderChild.setActivityFee(activityFee);
						orderChild.setActivityId(payCashBackEntity.getId());
						isCalProductActivity = true;
						isCanUseCoupon = false;
					}
				}
			}
			payAmount = payAmount.add(fee);
			logger.debug("price={},buyNumber={},fee={}", price,
					orderChild.getBuyNumber(), fee);
		}

		order.setActivityFee(null);
		order.setActivityId(null);
		order.setActivityType(null);

		FoodMachineEntity mac = foodMachineDao.findByMacId(order.getMacId());
		if (isCalProductActivity == false && mac.getActivitys() != null
				&& mac.getActivitys().contains(firstOrderActivityType)) {
			if(isCheckActivity){
				if (firstOrderActivity != null) {
					isCanUseCoupon = false;
					order.setActivityFee(firstOrderActivity.getAmount());
					order.setActivityId(firstOrderActivity.getId());
					order.setActivityType(firstOrderActivityType);
					payAmount = payAmount.subtract(firstOrderActivity.getAmount());
				}
			}
		}
		
		if (isCalProductActivity == false && mac.getActivitys() != null
				&& mac.getActivitys().contains("2")) {
			if(isCheckActivity){
				if(keepDiscountActivity.getJoinRestrict().compareTo(payAmount) >= 0){//满足参加活动状态
					Integer count = getActivityFor("2", keepDiscountActivity.getId(), order.getMacId(), null, userId, "2");
					if (count == 0) {//当天未参加
						Date start = keepDiscountActivity.getStartTime(),
								end = new Date();
						if(start.after(end)){
							logger.error("数据库时间与服务器时间不同步start={},end={}",start,end);
						}else{
							Calendar aCalendar = Calendar.getInstance();
					        aCalendar.setTime(start);
					        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
					        aCalendar.setTime(end);
					        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
					        int days = day2-day1;
					        
							BigDecimal initDis = keepDiscountActivity.getInitDis();
							BigDecimal keepDis = initDis.add(keepDiscountActivity.getProIncr().multiply(new BigDecimal(days))); 
							logger.debug("mac|cal before keepDis={},payAmount={}",keepDis,payAmount);
							if(keepDis.compareTo(new BigDecimal("10")) >= 0){
								keepDis = new BigDecimal("10");
							}
							
							BigDecimal activityFee = payAmount.multiply(keepDis.divide(new BigDecimal(10)));
							payAmount = payAmount.subtract(activityFee);
							logger.debug("mac|cal after activityFee={},payAmount={}",activityFee,payAmount);
							order.setActivityFee(activityFee);
							order.setActivityId(keepDiscountActivity.getId());
							order.setActivityType("2");
							isCanUseCoupon = false;
						}
					}
				}
			}
		}
		
		if (isCalProductActivity == false && mac.getActivitys() != null
				&& mac.getActivitys().contains("3")) {
			if(isCheckActivity){
				Integer count = getActivityFor("3", payCashBackEntity.getId(), order.getMacId(), null, null, null);
				logger.debug("mac|activity 3 count={},personTime={}",count,payCashBackEntity.getPersonTime());
				if(count < payCashBackEntity.getPersonTime()){//人数
					BigDecimal amount = payCashBackEntity.getAmount();
					BigDecimal activityFee = amount.subtract(amount.multiply(new BigDecimal(count)));
					logger.debug("mac|activity cal after payAmount={}",payAmount);
					order.setActivityFee(activityFee);
					order.setActivityId(payCashBackEntity.getId());
					order.setActivityType("3");
					isCanUseCoupon = false;
				}
			}
		}

		if (order.getCouponId() != null) {
			if (!isCanUseCoupon) {
				throw new I18nMessageException("70001", "活动中不能使用优惠券");
			}
			OperateCouponRecordEntity coupon = operateCouponRecordDao
					.findByIdAndUser(order.getCouponId(), order.getUserId());
			if (coupon == null) {
				throw new I18nMessageException("70002", "优惠券不存在或已过期");
			}
			if (coupon.getUse() == 1) {
				throw new I18nMessageException("70003", "优惠券已使用");
			}

			if (payAmount.compareTo(new BigDecimal(coupon.getUseRestrict())) < 0) {// 满足条件
				throw new I18nMessageException("70004", "不满足优惠券使用条件");
			}
			BigDecimal couponFee = null;
			if (coupon.getType() == 0) {// 现金券
				couponFee = coupon.getAmount();
			} else {// 折扣券
				couponFee = payAmount.multiply(new BigDecimal(10).subtract(
						coupon.getAmount()).divide(new BigDecimal(10)));
			}
			payAmount = payAmount.subtract(couponFee);
			order.setCouponFee(couponFee);
			order.setCouponType(coupon.getType());
			order.setCouponUseRestrict(coupon.getUseRestrict());
		} else {
			order.setCouponFee(null);
			order.setCouponType(null);
			order.setCouponUseRestrict(null);
		}
		if (dis != null) {
			logger.info("level dis={},payAmount={}", dis, payAmount);
			BigDecimal redFee = payAmount.subtract(payAmount.multiply(dis));
			order.setRedPackedAmount(redFee);
			payAmount = payAmount.subtract(redFee);
		}
		order.setBuyNumber(buyNumber);
		order.setOrderAmount(totalFee);
		order.setPayAmount(payAmount);
		return order;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public FoodOrderEntity createOrder(Long userId, BigDecimal dis,
			FoodOrderEntity order) {

		Date now = new Date();

		int count = this.baseMapper
				.selectCount(new EntityWrapper<FoodOrderEntity>().eq("user_id",
						userId).eq("order_status", 0));

		redisTemplate.opsForValue().get("order:config:timeout");
		Integer nopayMaxCount = (Integer) redisTemplate.opsForValue().get(
				"order:config:nopay_max_count");
		if (nopayMaxCount != null && count > nopayMaxCount) {
			throw new I18nMessageException("60006", "订单创建失败，您有" + count
					+ "个尚未支付订单，请立即处理!");
		}

		this.calCheckTotalFee(userId, dis, order, true, true);

		/*
		 * OperateActivityKeepDiscountEntity keepDiscountActivity =
		 * operateActivityKeepDiscountDao.findUnderwayActivity(); String
		 * keepDiscountKey = "operate:activitys:keepDiscount", payCashBackKey =
		 * "operate:activitys:payCashBack";
		 */

		String orderNo = getOrder(now);
		// TODO Auto-generated method stub
		order.setCreateTime(now);
		if(order.getTakeFoodTime() == null){
			order.setTakeFoodTime(now);
		}
		order.setUserId(userId);
		order.setOrderStatus(0);
		order.setOrderNo(orderNo);

		this.baseMapper.insert(order);

		List<FoodOrderChildsEntity> childs = order.getChilds();
		for (FoodOrderChildsEntity child : childs) {
			child.setOrderId(order.getId());
			FoodMachineAisleEntity aisle = new FoodMachineAisleEntity();
			aisle.setId(child.getAisleId());
			aisle = foodMachineAisleDao.selectOne(aisle);
			aisle.setStock(aisle.getStock() - child.getBuyNumber());
			foodMachineAisleDao.updateById(aisle);
			foodOrderChildsDao.insert(child);

			/*
			 * String key = keepDiscountKey+":product:"+
			 * if(!redisHash.hExists(key)){ Integer expire = (int)
			 * ((keepDiscountActivity.getStartTime().getTime() +
			 * (keepDiscountActivity.getPeriod()*24*60*60*1000))/1000);
			 * redisHash.hSet(keepDiscountKey, userId+"", 1, expire); }else{
			 * 
			 * }
			 */
		}

		if (order.getCouponId() != null) {// 修改优惠券
			OperateCouponRecordEntity coupon = operateCouponRecordDao
					.findByIdAndUser(order.getCouponId(), order.getUserId());
			coupon.setUse(1);
			coupon.setOrderNo(orderNo);
			coupon.setUseTime(new Date());
			operateCouponRecordDao.updateById(coupon);
		}
		return order;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public synchronized FoodOrderEntity payFoodOrder(String orderNo, String buyTime, String qrCode) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = this
				.selectOne(new EntityWrapper<FoodOrderEntity>().eq("order_no",
						orderNo));
		if (order == null) {
			throw new I18nMessageException("60007", "订单不存在");
		}
		Integer takeFoodCode = createTakeFoodCode(order.getMacId(), orderNo);
		order.setQrCode(qrCode);
		order.setOrderStatus(1);
		order.setTakeFoodCode(takeFoodCode);
		this.baseMapper.updateById(order);
		
		putActivity(order,buyTime);
		
		return order;
	}
	
	private void putActivity(FoodOrderEntity order, String buyTime){
		if(order == null || order.getUserId() == null){
			return;
		}
		String activity = order.getActivityType();
		Integer macId = order.getMacId();
		Long userId = order.getUserId();
		
		if(StringUtils.isBlank(order.getActivityType())){
			//产品是否有活动
			List<FoodOrderChildsEntity> childs = foodOrderChildsDao
					.selectList(new EntityWrapper<FoodOrderChildsEntity>().eq(
							"user_id", userId).eq("order_id", order.getId()));
			for(FoodOrderChildsEntity child : childs){
				if("3".equals(order.getActivityType())){
					String logType = "ACTIVITY_ORDER_BACK_MONEY";
					Map<String,String> pmessage = new HashMap<String,String>();
					
					String unq = order.getOrderNo()+"_"+child.getActivity()+"_mac_"+order.getMacId()+"product_"+child.getProductId();
					pmessage.put("out_trade_no", order.getOrderNo());
					pmessage.put("recharge_fee", child.getActivityFee().toString());
					pmessage.put("log_type", logType);
					pmessage.put("descr", "活动返现");
					pmessage.put("adjunctInfo", unq);
					pmessage.put("user_id", order.getUserId()+"");
					pmessage.put("buy_time", buyTime);
					pmessage.put("token", MD5.md5(logType.toString()+"_"+unq));
					
					rabbitmqSender.sendContractDirect("account.balance.queue.award",
	    					new Gson().toJson(pmessage));
				}
				putActivityFor(child.getActivity(), order.getActivityId(), macId, child.getProductId(), userId);
			}
		}else{
			//处理机器活动
			if(StringUtils.isNotBlank(order.getActivityType())){
				if("3".equals(order.getActivityType())){//通知返现队列
					String logType = "ACTIVITY_ORDER_BACK_MONEY";
					Map<String,String> pmessage = new HashMap<String,String>();
					String unq = order.getOrderNo()+"_"+activity+"_mac_"+order.getMacId();
					pmessage.put("out_trade_no", order.getOrderNo());
					pmessage.put("recharge_fee", order.getActivityFee().toString());
					pmessage.put("log_type", logType);
					pmessage.put("descr", "活动返现");
					pmessage.put("adjunctInfo", unq);
					pmessage.put("user_id", order.getUserId()+"");
					pmessage.put("buy_time", buyTime);
					pmessage.put("token", MD5.md5(logType.toString()+"_"+unq));
					
					rabbitmqSender.sendContractDirect("account.balance.queue.award",
	    					new Gson().toJson(pmessage));
				}
				putActivityFor(activity, order.getActivityId(), macId, null, userId);
			}
		}
	}
	
	private Integer putActivityFor(String activity,Integer activityId,Integer macId,Integer productId,Long userId){
		HashOperations<String, String, Integer> operations = redisTemplate
				.opsForHash();
		String hashKey = Long.toString(userId);
		
		if("2".equals(activity)){//累计折扣活动，每人每天只能参加一次
			hashKey += "_"+DateFormatUtils.format(new Date(), "yyyyMMdd");
		}
		String	key = "activitys:" + activity + ":" + activityId +":"+macId;
		if(productId != null){
			key += ":"+productId;
		}
		if(operations.hasKey(key, hashKey)){
			operations.increment(key, hashKey, 1);
		}else{
			operations.put(key, hashKey, 1);
		}
		List<Integer> values = operations.values(key);
		Integer total = 0;
		for(Integer item : values){
			total += item;
		}
		return total;
	}
	
	private Integer getActivityFor(String activity,Integer activityId,Integer macId,Integer productId,Long userId,String flag){
		HashOperations<String, String, Integer> operations = redisTemplate
				.opsForHash();
		
		String	key = "activitys:" + activity + ":" + activityId + ":"+macId;
		if(productId != null){
			key += ":"+productId;
		}
		Integer total = 0;
		if(userId == null){
			List<Integer> values = operations.values(key);
			for(Integer item : values){
				total += item;
			}
		}else{
			String hashKey = Long.toString(userId);
			if("2".equals(flag)){//累计折扣活动，每人每天只能参加一次
				hashKey += "_"+DateFormatUtils.format(new Date(), "yyyyMMdd");
			}
			if(operations.hasKey(key, hashKey)){
				total = operations.get(key, hashKey);
			}
		}
		return total;
	}

	@Override
	public Map<String, Object> getSweeptCodeOrderInfo(String orderId) {
		return this.baseMapper.getSweeptCodeOrderInfo(orderId);
	}
	
	@Override
	public List<Map<String, Object>> findOrderInfoByMacIMEI(String imei) {
		// TODO Auto-generated method stub
		return this.baseMapper.findOrderInfoByMacIMEI(imei);
	}

	@Override
	public ResponseMessage pushOrder(Long orderId) {
		HashOperations<String, Long, FoodOrderEntity> operations = redisTemplate
				.opsForHash();
		// 查询订单是否已经加入队列
		try {
			FoodOrderEntity foodOrderCache = operations.get(ORDER_QUEUE_KEY,
					orderId);
			if (foodOrderCache != null && foodOrderCache.getId() > 0) {
				return ResponseMessage.ok(foodOrderCache);
			}
			// 查询订单，验证信息
			FoodOrderEntity foodOrderEntity = selectById(orderId);
			if (foodOrderEntity == null || foodOrderEntity.getId() == 0) {
				return ResponseMessage.error("00002", "数据异常");
			}
			// 订单状校验，只有已支付的订单才能有些操作
			if (foodOrderEntity.getOrderStatus() != 1) {
				return ResponseMessage.error("00003", "该订单未支付，请先完成支付！");
			}
			redisTemplate.setEnableTransactionSupport(true);
			redisTemplate.multi();
			// 当前排队数
			Long num = operations.size(ORDER_QUEUE_KEY);
			operations.put(ORDER_QUEUE_KEY, orderId, foodOrderEntity);
			List<Object> list = redisTemplate.exec();
			redisTemplate.setEnableTransactionSupport(false);
			list.forEach(e -> System.out.print(e));

			Map<String, Object> result = Maps.newHashMap();
			result.put("orderId", orderId);
			result.put("orderNo", foodOrderEntity.getOrderNo());
			result.put("currentNum", num);
			return ResponseMessage.ok(result);
		} catch (Error e) {
			e.printStackTrace();
			return ResponseMessage.error("00005", e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ResponseMessage takeFoodCallback(String orderId) {
		// 更新订单状
		FoodOrderEntity foodOrder = new FoodOrderEntity();
		foodOrder.setId(Long.parseLong(orderId));
		// 已取餐
		foodOrder.setOrderStatus(2);
		EntityWrapper<FoodOrderEntity> foodOrderWrapper = new EntityWrapper<FoodOrderEntity>();
		foodOrderWrapper.eq("id", orderId);
		// 必需要已支付订单才能操作
		foodOrderWrapper.eq("order_status", 1);
		if (update(foodOrder, foodOrderWrapper)) {
			// 从队列中踢除
			HashOperations<String, Long, FoodOrderEntity> operations = redisTemplate
					.opsForHash();
			long ret = operations.delete(ORDER_QUEUE_KEY, orderId);
			if (ret == 1) {
				foodOrder = selectById(orderId);
				Map<String, Object> resultData = Maps.newHashMap();
				resultData.put("orderId", orderId);
				resultData.put("orderNo", foodOrder.getOrderNo());
				return ResponseMessage.ok(resultData);
			}
		}
		return ResponseMessage.error("0004", "取餐失败!");
	}

	@Override
	public List<FoodOrderEntity> findOrderByUser(Long userId, Integer status,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return this.baseMapper.findOrderByUser(userId, status, size, offset);
	}
	
	@Override
	public FoodOrderEntity findOrderInfoByUser(Long userId, String orderNo) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = this.baseMapper.findOrderInfoByUser(userId, orderNo); 
		
		String key = "take:food:code:" + order.getMacId();
		HashOperations<String, String, String> operations = redisTemplate
				.opsForHash();
		//operations.get(key, orderNo);
		return order;
	}

	@Override
	public ResponseMessage getOrderNoByCode(Long macId, Integer takeFoodCode) {
		if (macId == null || macId == 0)
			return ResponseMessage.error("600003", "缺少机器ID");
		String key = "take:food:code:" + macId;
		String takeFoodCodeStr=Integer.toString(takeFoodCode);
		HashOperations<String, String, String> operations = redisTemplate
				.opsForHash();
		logger.debug("operations.get({},{})={}",key,takeFoodCodeStr,operations.get(key,takeFoodCodeStr));
		String takeFoodJson = operations.get(key, takeFoodCodeStr);
		TakeFoodCodeEntity takeFoodCodeEntity =JSON.parseObject(takeFoodJson,TakeFoodCodeEntity.class);
		if (takeFoodCodeEntity == null
				|| takeFoodCodeEntity.getOrderNo() == null)
			return ResponseMessage.error("600004", "数据异常");
		Map<String,String> result=Maps.newHashMap();
		result.put("orderNo",takeFoodCodeEntity.getOrderNo());
		return ResponseMessage.ok(result);
	}

	@Override
	public Integer createTakeFoodCode(Integer macId, String orderNo) {
		if (macId == null || macId == 0)
			throw new I18nMessageException("600003", "缺少机器ID");
		String key = "take:food:code:" + macId;
		HashOperations<String, String, String> operations = redisTemplate
				.opsForHash();
		System.out.println(operations.entries(key));
		TakeFoodCodeEntity takeFoodCode = new TakeFoodCodeEntity(null,orderNo,
				new Date().getTime());
		String  takeFoodStr=JSON.toJSONString(takeFoodCode);
		// 创建随机码
		int i = 0;
		boolean isOk = false;
		Integer codeStr = null;
		while (i < 10 && !isOk) {// 只尝试10次
			int code = (int) ((Math.random() * 9 + 1) * 100000);
			codeStr = code;
			isOk = operations.putIfAbsent(key, codeStr+"", takeFoodStr);
			i++;
		}
		return codeStr;
	}

	public void initTakeFoodCodePool(Long macId, String orderNo) {
		String keyQueue = "take:food:code:queue" + macId;
		ListOperations<String, Object> operations = redisTemplate.opsForList();
		operations.size(keyQueue);
		for (int i = 0; i < 10; i++) {
			Integer code = (int) ((Math.random() * 9 + 1) * 100000);
			operations.rightPushIfPresent(keyQueue, code);
		}
	}

	public ResponseMessage getOrderStatus(Long orderId,String orderNo){
		EntityWrapper<FoodOrderEntity> foodOrderWrapper=new EntityWrapper<FoodOrderEntity>();
		if (orderId!=null&&orderId>0)
			foodOrderWrapper.eq("id",orderId);
		if (StringUtils.isNotEmpty(orderNo))
			foodOrderWrapper.eq("order_no",orderNo);
		FoodOrderEntity foodOrderEntity=this.selectOne(foodOrderWrapper);
		Map<String,Object> result=Maps.newHashMap();
		result.put("orderId",foodOrderEntity.getId());
		result.put("orderNo",foodOrderEntity.getOrderNo());
		result.put("status",foodOrderEntity.getOrderStatus());
		result.put("orderTime",foodOrderEntity.getCreateTime());
		return ResponseMessage.ok(result);
	}
}