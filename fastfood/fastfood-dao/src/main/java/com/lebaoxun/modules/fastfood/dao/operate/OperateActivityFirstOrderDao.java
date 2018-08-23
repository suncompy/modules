package com.lebaoxun.modules.fastfood.dao.operate;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;

/**
 * 首单活动表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:31
 */
@Mapper
public interface OperateActivityFirstOrderDao extends BaseMapper<OperateActivityFirstOrderEntity> {
	OperateActivityFirstOrderEntity findUnderwayActivity();
	
}
