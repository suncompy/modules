package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 取餐机预定时间配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineAdvanceTimeService extends IService<FoodMachineAdvanceTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<FoodMachineAdvanceTimeEntity> findAdvanceTimeByMacId(Integer macId);

    List<FoodMachineAdvanceTimeEntity> findPreOrderAndProByMacId(Integer macId);
}

