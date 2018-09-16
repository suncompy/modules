package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineAddStockOrderService extends IService<FoodMachineAddStockOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> queryReplenishManList(String userName,String mobile,String createTime);
    List<Map<String,Object>> queryPickingManList(String userName,String mobile,String createTime);
}

