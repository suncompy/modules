package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineProActivityRefService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:24
 */
@RestController
public class FoodMachineProActivityRefController {
    @Autowired
    private FoodMachineProActivityRefService foodMachineProActivityRefService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineProActivityRefService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineProActivityRefEntity foodMachineProActivityRef = foodMachineProActivityRefService.selectById(id);
        return ResponseMessage.ok().put("foodMachineProActivityRef", foodMachineProActivityRef);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/save")
    @RedisLock(value="fastfood:foodmachineproactivityref:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProActivityRefEntity foodMachineProActivityRef){
		foodMachineProActivityRefService.insert(foodMachineProActivityRef);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/update")
    @RedisLock(value="fastfood:foodmachineproactivityref:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProActivityRefEntity foodMachineProActivityRef){
		foodMachineProActivityRefService.updateById(foodMachineProActivityRef);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/delete")
    @RedisLock(value="fastfood:foodmachineproactivityref:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineProActivityRefService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
