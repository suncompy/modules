package com.lebaoxun.modules.fastfood.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;


@Service("foodOrderService")
public class FoodOrderServiceImpl extends ServiceImpl<FoodOrderDao, FoodOrderEntity> implements FoodOrderService {
	private Logger logger = LoggerFactory.getLogger(getClass());
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

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<FoodOrderEntity> createOrder(Long userId,
			List<FoodOrderEntity> orders) {
		// TODO Auto-generated method stub
		
		/*List<Long> productSpecIds = new ArrayList<Long>();
		for (MallCartEntity mope : products) {
			if (mope.getProductSpecId() != null) {
				productSpecIds.add(mope.getProductSpecId());
			}
		}
		Date now = new Date();
		List<MallProductCartVo> mpcvs = mallCartDao
				.queryByProductSpecId(productSpecIds.toArray(new Long[] {}));
		if (mpcvs.size() != products.size()) {
			throw new I18nMessageException("-1", "商品不存在或已下架");
		}
		int count = this.baseMapper
				.selectCount(new EntityWrapper<MallOrderEntity>().eq("user_id",
						userId).eq("order_status", 0));
		if (count >= maxOrderNum) {
			throw new I18nMessageException("20110", "订单创建失败，您还有" + count
					+ "个未支付订单，立即去处理!");
		}
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
		String orderNo = "lv" + date + format1(inr, 5);
		logger.debug("orderNo={}", orderNo);

		MallOrderEntity order = new MallOrderEntity();
		order.setCreateTime(now);
		order.setUserId(userId);
		order.setOrderStatus(0);
		order.setPayType(0);// 在线支付
		order.setShipmentAmount(new BigDecimal(0));// 快递费
		order.setOrderNo(orderNo);
		Integer totalBuyNumber = 0, orderScore = 0;
		BigDecimal totalMoney = new BigDecimal(0);
		List<MallOrderProductEntity> mopes = new ArrayList<MallOrderProductEntity>();
		for (MallProductCartVo mpcv : mpcvs) {
			MallProductSpecificationEntity mpse = mpcv.getSpec();
			MallProductEntity mpe = mpcv.getProduct();
			if (mpse == null || mpse.getStatus() != 1) {
				throw new I18nMessageException("-1", "“" + mpe.getName()
						+ "”商品不存在或已下架");
			}
			MallCartEntity cart = products.get(productSpecIds.indexOf(mpcv
					.getSpec().getProductSpecId()));
			if (cart.getBuyNumber() == null) {
				throw new I18nMessageException("-1", "请输入正确的商品数量");
			}
			if (mpse.getStock() <= 0 || mpse.getStock() < cart.getBuyNumber()) {
				throw new I18nMessageException("-1", "“" + mpe.getName()
						+ "”商品 规格”" + mpse.getSpecName() + " "
						+ mpse.getSpecAttrName() + "”库存不足");
			}
			totalBuyNumber += cart.getBuyNumber();
			BigDecimal money = mpse.getPrice().multiply(
					new BigDecimal(cart.getBuyNumber()));
			logger.debug("mpse.getPrice={},money={}", mpse.getPrice(), money);
			totalMoney = totalMoney.add(money);
			orderScore += mpse.getScore() * cart.getBuyNumber();

			MallOrderProductEntity mope = new MallOrderProductEntity();
			mope.setProductId(mpe.getId());
			mope.setBuyNumber(cart.getBuyNumber());
			mope.setName(mpe.getName());
			mope.setPicImg(mpe.getShowPic());
			mope.setPrice(mpse.getPrice());
			mope.setProductAmount(money);
			mope.setProductScore(0);
			mope.setProductSpecId(mpcv.getSpec().getProductSpecId());
			mope.setProductSpecName(mpcv.getSpecName() + " "
					+ mpcv.getSepcAttrName());
			mope.setScore(mpse.getScore());
			mope.setStatus(0);// 待发货
			mopes.add(mope);
		}

		logger.debug("totalMoney={}", totalMoney);
		logger.debug("totalBuyNumber={}", totalBuyNumber);
		order.setPayAmount(totalMoney);
		order.setOrderAmount(totalMoney);
		order.setBuyNumber(totalBuyNumber);
		order.setOrderScore(orderScore);
		this.baseMapper.save(order);

		for (MallOrderProductEntity mope : mopes) {
			mope.setOrderId(order.getId());
			mallOrderProductDao.insert(mope);
		}

		mallCartDao.delete(new EntityWrapper<MallCartEntity>().eq("user_id",
				userId).in("product_spec_id", productSpecIds));
		return orderNo;*/
		return null;
	}

}
