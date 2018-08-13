package com.lebaoxun.modules.fastfood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;

/**
 * 餐品原料表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@Mapper
public interface FoodMaterialDao extends BaseMapper<FoodMaterialEntity> {
	
	List<FoodMaterialEntity> queryByProductCatId(@Param("productCatId")Integer productCatId);
	
}
