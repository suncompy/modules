package com.lebaoxun.modules.operate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;

/**
 * 优惠券领取记录
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
@Mapper
public interface OperateCouponRecordDao extends BaseMapper<OperateCouponRecordEntity> {
	
	/**
	 * 查询用户未过期，未使用的优惠券
	 * @param userId
	 * @return
	 */
	List<OperateCouponRecordEntity> findByUserId(@Param("userId")Long userId,
			@Param("macId")Integer macId,
			@Param("use")Integer use,
			@Param("flag")Integer flag,
			@Param("size")Integer size, @Param("offset")Integer offset);
	
	OperateCouponRecordEntity findByIdAndUser(
			@Param("id")Integer id,
			@Param("userId")Long userId);
}