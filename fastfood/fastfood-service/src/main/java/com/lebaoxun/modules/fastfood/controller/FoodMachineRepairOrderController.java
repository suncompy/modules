package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineRepairOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 维修派单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineRepairOrderController {
    @Autowired
    private FoodMachineRepairOrderService foodMachineRepairOrderService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineRepairOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineRepairOrderEntity foodMachineRepairOrder = foodMachineRepairOrderService.selectById(id);
        return ResponseMessage.ok().put("foodMachineRepairOrder", foodMachineRepairOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/save")
    @RedisLock(value="fastfood:foodmachinerepairorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder){
        foodMachineRepairOrder.setCreateBy(adminId);
        foodMachineRepairOrder.setCreateTime(new Date());
        foodMachineRepairOrder.setStatus(0);
        foodMachineRepairOrder.setUpdateTime(new Date());
		foodMachineRepairOrderService.insert(foodMachineRepairOrder);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/update")
    @RedisLock(value="fastfood:foodmachinerepairorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder){
		foodMachineRepairOrderService.updateById(foodMachineRepairOrder);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/delete")
    @RedisLock(value="fastfood:foodmachinerepairorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineRepairOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
