package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 购物车表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 18:47:10
 */
@Mapper
public interface FoodShoppingCartDao extends BaseMapper<FoodShoppingCartEntity> {
	
}
