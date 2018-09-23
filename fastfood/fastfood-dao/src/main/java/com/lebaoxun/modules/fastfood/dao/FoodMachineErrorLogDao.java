package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodMachineErrorLogEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 终端机器错误日志表
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-23 19:35:01
 */
@Mapper
public interface FoodMachineErrorLogDao extends BaseMapper<FoodMachineErrorLogEntity> {
	
}
