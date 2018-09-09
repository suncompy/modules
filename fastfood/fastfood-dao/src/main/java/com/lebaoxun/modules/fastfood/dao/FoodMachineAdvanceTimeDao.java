package com.lebaoxun.modules.fastfood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;

/**
 * 取餐机预定时间配置
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineAdvanceTimeDao extends BaseMapper<FoodMachineAdvanceTimeEntity> {
	
	List<FoodMachineAdvanceTimeEntity> findAdvanceTimeByMacId(@Param("macId")Integer macId);

	List<FoodMachineAdvanceTimeEntity> findPreOrderAndProByMacId(@Param("macId")Integer macId);
}
