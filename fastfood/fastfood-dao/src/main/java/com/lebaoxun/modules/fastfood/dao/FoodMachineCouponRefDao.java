package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface FoodMachineCouponRefDao extends BaseMapper<FoodMachineCouponRefEntity> {
    public List<FoodMachineCouponRefEntity> findMacCouponListByMacId(@Param("macId")Integer macId);
}
