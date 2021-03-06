package com.lebaoxun.modules.fastfood.dao.operate;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;

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
	
	void closeAllActivity();
}
