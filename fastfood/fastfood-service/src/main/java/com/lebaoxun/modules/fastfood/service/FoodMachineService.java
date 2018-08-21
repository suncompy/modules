package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;

/**
 * 取餐机
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineService extends IService<FoodMachineEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void save(Long adminId,FoodMachineEntity foodMachine);

    public void updateById(Long adminId,FoodMachineEntity foodMachine);
    
    /**
     * 根据省市区搜索机器列表
     * @param areaCode
     * @return
     */
    List<FoodMachineEntity> findByAreaCode(String areaCode);
    
    /**
     * 查询机器详情
     * @param macId
     * @return
     */
    FoodMachineEntity findByMacId(Integer macId);


    Map<String,Object> findByMacOpenApiById(Integer macId);

    /**
     *查询机器关联产品
     */
    List<Map<String, Object>> findByMacRefProductById(Integer macId);
}

