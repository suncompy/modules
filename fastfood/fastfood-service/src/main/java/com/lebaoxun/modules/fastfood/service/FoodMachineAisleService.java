package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;

/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineAisleService extends IService<FoodMachineAisleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void refProductAndType(Long adminId,FoodMachineRefAisleEntity foodMachineAisle);

    List<FoodMachineRefAisleEntity> findMachineAisleListByMacId(Integer macId);
    
    /**
     * 根据机器查询产品
     * @param macId
     * @param productCatId
     * @return
     */
    List<Map<String, Object>> findProductByMacIdAndProductCatId(Integer macId, Integer productCatId);
    
}

