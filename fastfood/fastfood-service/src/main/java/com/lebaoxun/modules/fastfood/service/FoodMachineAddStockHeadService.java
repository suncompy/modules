package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 取餐机进货派单主表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-25 16:41:14
 */
public interface FoodMachineAddStockHeadService extends IService<FoodMachineAddStockHeadEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean acceptPickingOrder(Long userId,FoodMachineAddStockHeadEntity foodMachineAddStockHead);

    ResponseMessage createPickingOreder(Long adminId, List<FoodMachineAddStockOrderEntity> addStockOrderList);

    ResponseMessage sendMsg(Integer macId);
}

