package com.lebaoxun.modules.mall.dao;

import java.util.List;

import com.lebaoxun.modules.mall.entity.MallProductSpecificationEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 商品规格表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-07-12 19:57:12
 */
@Mapper
public interface MallProductSpecificationDao extends BaseMapper<MallProductSpecificationEntity> {
	List<MallProductSpecificationEntity> queryByProductId(@Param("productId")Long productId);
}
