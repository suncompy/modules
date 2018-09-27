package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockHeadService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机进货派单主表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-25 16:41:14
 */
@RestController
public class FoodMachineAddStockHeadController {
    @Autowired
    private FoodMachineAddStockHeadService foodMachineAddStockHeadService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineAddStockHeadService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineAddStockHeadEntity foodMachineAddStockHead = foodMachineAddStockHeadService.selectById(id);
        return ResponseMessage.ok().put("foodMachineAddStockHead", foodMachineAddStockHead);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/save")
    @RedisLock(value="fastfood:foodmachineaddstockhead:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockHeadEntity foodMachineAddStockHead){
		foodMachineAddStockHeadService.insert(foodMachineAddStockHead);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/update")
    @RedisLock(value="fastfood:foodmachineaddstockhead:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockHeadEntity foodMachineAddStockHead){
        boolean ret=foodMachineAddStockHeadService.acceptPickingOrder(adminId,foodMachineAddStockHead);
        if(ret)
            return ResponseMessage.ok();
        else
            return ResponseMessage.error("60001","接受派单失败!");
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/delete")
    @RedisLock(value="fastfood:foodmachineaddstockhead:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineAddStockHeadService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
