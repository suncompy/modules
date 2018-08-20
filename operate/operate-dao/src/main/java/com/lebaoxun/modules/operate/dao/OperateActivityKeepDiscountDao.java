package com.lebaoxun.modules.operate.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.operate.entity.OperateActivityKeepDiscountEntity;

/**
 * 连续折扣
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */
@Mapper
public interface OperateActivityKeepDiscountDao extends BaseMapper<OperateActivityKeepDiscountEntity> {
	OperateActivityKeepDiscountEntity findUnderwayActivity();
}
