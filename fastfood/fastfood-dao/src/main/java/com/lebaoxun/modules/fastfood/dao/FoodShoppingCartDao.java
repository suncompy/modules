package com.lebaoxun.modules.fastfood.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;

/**
 * 购物车表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodShoppingCartDao extends BaseMapper<FoodShoppingCartEntity> {
	List<Map<String,Object>> findByUser(@Param("userId") Long userId);
}
