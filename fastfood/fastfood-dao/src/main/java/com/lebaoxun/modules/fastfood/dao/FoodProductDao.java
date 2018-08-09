package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 餐品表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@Mapper
public interface FoodProductDao extends BaseMapper<FoodProductEntity> {
	
}
