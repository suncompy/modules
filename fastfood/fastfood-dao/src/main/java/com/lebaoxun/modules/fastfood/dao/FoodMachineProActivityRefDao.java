package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:24
 */
@Mapper
public interface FoodMachineProActivityRefDao extends BaseMapper<FoodMachineProActivityRefEntity> {
    public List<FoodMachineProActivityRefEntity> foodMachineProActListByMacId(@Param("macId")Integer macId);
}
