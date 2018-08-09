package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 订单表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 18:47:10
 */
@Mapper
public interface FoodOrderDao extends BaseMapper<FoodOrderEntity> {
	
}
