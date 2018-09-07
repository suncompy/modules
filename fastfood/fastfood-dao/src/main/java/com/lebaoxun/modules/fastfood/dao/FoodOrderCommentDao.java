package com.lebaoxun.modules.fastfood.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentEntity;

/**
 * 评价表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
@Mapper
public interface FoodOrderCommentDao extends BaseMapper<FoodOrderCommentEntity> {
void save(FoodOrderCommentEntity comment);
	
	List<FoodOrderCommentEntity> selectByMacId(@Param("macId") Integer macId);
	
	FoodOrderCommentEntity selectLastByMacId(@Param("macId") Integer macId);
}
