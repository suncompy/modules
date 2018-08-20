package com.lebaoxun.modules.operate.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.operate.dao.OperateCouponDao;
import com.lebaoxun.modules.operate.dao.OperateCouponRecordDao;
import com.lebaoxun.modules.operate.entity.OperateCouponEntity;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.OperateCouponRecordService;


@Service("operateCouponRecordService")
public class OperateCouponRecordServiceImpl extends ServiceImpl<OperateCouponRecordDao, OperateCouponRecordEntity> implements OperateCouponRecordService {

	@Resource
	private OperateCouponDao operateCouponDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateCouponRecordEntity> page = this.selectPage(
                new Query<OperateCouponRecordEntity>(params).getPage(),
                new EntityWrapper<OperateCouponRecordEntity>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public List<OperateCouponRecordEntity> findByUserId(Long userId,
    		Integer use,Integer flag,
    		Integer size, Integer offset) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findByUserId(userId,use,flag,size,offset);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public OperateCouponRecordEntity getCouponByCouponId(Long userId,
    		Integer macId,Integer couponId) {
    	OperateCouponEntity coupon = operateCouponDao.findById(macId, couponId);
    	if(coupon == null){
    		throw new I18nMessageException("60001","产品不存在或已过期");
    	}
    	int count = this.baseMapper.selectCount(new EntityWrapper<OperateCouponRecordEntity>().eq("coupon_id", couponId).eq("user_id", userId).eq("mac_id", macId));
    	if(coupon.getPeoNumRestrict() <= count){
    		throw new I18nMessageException("60002","每人限领"+coupon.getPeoNumRestrict()+"次");
    	}
    	if(coupon.getTotal() <= 0){
    		throw new I18nMessageException("60003","对不起，已领取完！");
    	}
    	OperateCouponRecordEntity couponRecord = new OperateCouponRecordEntity();
    	couponRecord.setAmount(coupon.getAmount());
    	couponRecord.setCouponId(couponId);
    	couponRecord.setCreateTime(new Date());
    	couponRecord.setMacId(macId);
    	couponRecord.setName(coupon.getName());
    	couponRecord.setPeriod(coupon.getPeriod());
    	couponRecord.setStartTime(coupon.getStartTime());
    	couponRecord.setType(coupon.getType());
    	couponRecord.setUse(0);
    	couponRecord.setUseRestrict(coupon.getUseRestrict());
    	couponRecord.setUserId(userId);
    	this.baseMapper.insert(couponRecord);
    	
    	coupon.setTotal(coupon.getTotal() - 1);
    	operateCouponDao.updateById(coupon);
    	return couponRecord;
    }
}
