package com.lebaoxun.modules.account.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.account.entity.UserLogEntity;

/**
 * 
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-06-19 20:01:34
 */
@Mapper
public interface UserLogDao extends BaseMapper<UserLogEntity> {
	
	List<Map<String,Object>> queryAllLogType();
	
	List<UserLogEntity> queryAccountLogByUserId(@Param("user_id")Long userId,
			@Param("flag")String flag, 
			@Param("logType")String logType, 
			@Param("size")Integer size,
			@Param("offset")Integer offset);
	
	BigDecimal sumTradeMoneyByUser(@Param("userId") Long userId,
			@Param("time") String time,
			@Param("logType") String logType);
	
	List<UserLogEntity> queryUserLogByDay(@Param("user_id")long userId,
			@Param("adjunctInfo")String adjunctInfo, 
			@Param("dayTime")String dayTime);
	
	BigDecimal sumTradeMoneyByUserIdAndLogTypeAndTime(@Param("userId") Long userId,
			@Param("logType") String logType,
			@Param("start") String start,
			@Param("end") String end
			);
}
