package com.lebaoxun.modules.fastfood.dao;

import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 取餐机
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineDao extends BaseMapper<FoodMachineEntity> {
   
   public void save(FoodMachineEntity foodMachineEntity);
   
   /**
    * 根据省市区搜索机器列表
    * @param areaCode
    * @return
    */
   List<FoodMachineEntity> findByAreaCode(@Param("areaCode")String areaCode);
   
   /**
    * 查询机器详情
    * @param macId
    * @return
    */
   FoodMachineEntity findByMacId(@Param("macId")Integer macId);

   Map<String, Object> findByMacOpenApiById(@Param("macId")Integer macId);

   List<Map<String, Object>> findByMacRefProductById(@Param("macId")Integer macId);
}
