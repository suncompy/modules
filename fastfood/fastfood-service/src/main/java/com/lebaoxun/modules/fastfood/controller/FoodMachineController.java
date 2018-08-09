package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:11
 */
@RestController
public class FoodMachineController {
    @Autowired
    private FoodMachineService foodMachineService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachine/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachine/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineEntity foodMachine = foodMachineService.selectById(id);
        return ResponseMessage.ok().put("foodMachine", foodMachine);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachine/save")
    @RedisLock(value="fastfood:foodmachine:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineEntity foodMachine){
		foodMachineService.insert(foodMachine);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachine/update")
    @RedisLock(value="fastfood:foodmachine:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineEntity foodMachine){
		foodMachineService.updateById(foodMachine);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachine/delete")
    @RedisLock(value="fastfood:foodmachine:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
