package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMachineAisleController {
    @Autowired
    private FoodMachineAisleService foodMachineAisleService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineAisleService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineAisleEntity foodMachineAisle = foodMachineAisleService.selectById(id);
        return ResponseMessage.ok().put("foodMachineAisle", foodMachineAisle);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaisle/save")
    @RedisLock(value="fastfood:foodmachineaisle:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAisleEntity foodMachineAisle){
		foodMachineAisleService.insert(foodMachineAisle);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaisle/update")
    @RedisLock(value="fastfood:foodmachineaisle:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAisleEntity foodMachineAisle){
		foodMachineAisleService.updateById(foodMachineAisle);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaisle/delete")
    @RedisLock(value="fastfood:foodmachineaisle:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineAisleService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
