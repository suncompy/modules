package com.lebaoxun.modules.operate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.operate.entity.OperateCouponEntity;

/**
 * 优惠券
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:33
 */
@Mapper
public interface OperateCouponDao extends BaseMapper<OperateCouponEntity> {
	
	List<OperateCouponEntity> findByMacId(@Param("macId")Integer macId,@Param("userId") Long userId);
	
	OperateCouponEntity findById(@Param("macId")Integer macId,@Param("couponId")Integer couponId);
}
