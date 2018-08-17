package com.lebaoxun.modules.fastfood.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:23
 */
@Mapper
public interface FoodMachineActivityRefDao extends BaseMapper<FoodMachineActivityRefEntity> {
    public List<FoodMachineActivityRefEntity> findMacActivityListByMacId(@Param("macId")Integer macId);
}
