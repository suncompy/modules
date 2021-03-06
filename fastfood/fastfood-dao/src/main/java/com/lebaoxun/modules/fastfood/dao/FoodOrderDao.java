package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodOrderDao extends BaseMapper<FoodOrderEntity> {
	Map<String, Object> getSweeptCodeOrderInfo(@Param("orderNo") String orderNo);
	
	List<Map<String, Object>> findOrderInfoByMacIMEI(@Param("imei") String  imei);

	List<FoodOrderEntity> findOrderByUser(@Param("userId") Long userId,
			@Param("status") List<Integer> status, 
			@Param("size") Integer size, 
			@Param("offset") Integer offset);
	
	List<FoodOrderEntity> findByNopayAndTimeout(@Param("timeOut") Integer timeOut);
	
	void closeOrderByNopayAndTimeout(@Param("timeOut") Integer timeOut);
	
	FoodOrderEntity findOrderInfoByUser(@Param("userId") Long userId, 
			@Param("orderNo") String orderNo);
	
	List<FoodOrderEntity> findPager(Map<String,Object> map);
	
	int countPager(Map<String,Object> map);
}
