package com.lebaoxun.modules.fastfood.dao;

import java.util.List;

import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 取餐机餐品分类表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineProductCatDao extends BaseMapper<FoodMachineProductCatEntity> {
	List<FoodMachineProductCatEntity> findCatByMacId(@Param("macId")Integer macId);
}
