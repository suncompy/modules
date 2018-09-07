package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 取餐机分类通道
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineCatAisleDao extends BaseMapper<FoodMachineCatAisleEntity> {
    public List<Map<String,Object>> findAisleInfoByCatId(@Param("catId")Integer catId);
}
