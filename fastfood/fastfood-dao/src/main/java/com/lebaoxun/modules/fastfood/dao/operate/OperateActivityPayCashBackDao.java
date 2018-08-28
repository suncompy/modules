package com.lebaoxun.modules.fastfood.dao.operate;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityPayCashBackEntity;

/**
 * 充值返现表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:30
 */
@Mapper
public interface OperateActivityPayCashBackDao extends BaseMapper<OperateActivityPayCashBackEntity> {
	OperateActivityPayCashBackEntity findUnderwayActivity();
}
