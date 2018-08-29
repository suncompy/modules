package com.lebaoxun.modules.fastfood.dao.operate;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeGetLogEntity;

/**
 * 抽奖记录表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@Mapper
public interface OperatePrizeGetLogDao extends BaseMapper<OperatePrizeGetLogEntity> {
	
	List<OperatePrizeGetLogEntity> findLogByUserId(@Param("userId")Long userId,
			@Param("status")Integer status,
			@Param("size")Integer size,
			@Param("offset")Integer offset);
	
	OperatePrizeGetLogEntity findLogById(@Param("userId")Long userId,
			@Param("id")Integer id);
	
}
