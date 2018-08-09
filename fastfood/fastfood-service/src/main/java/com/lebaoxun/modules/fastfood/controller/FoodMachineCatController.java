package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:11
 */
@RestController
public class FoodMachineCatController {
    @Autowired
    private FoodMachineCatService foodMachineCatService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineCatService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineCatEntity foodMachineCat = foodMachineCatService.selectById(id);
        return ResponseMessage.ok().put("foodMachineCat", foodMachineCat);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecat/save")
    @RedisLock(value="fastfood:foodmachinecat:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatEntity foodMachineCat){
		foodMachineCatService.insert(foodMachineCat);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecat/update")
    @RedisLock(value="fastfood:foodmachinecat:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatEntity foodMachineCat){
		foodMachineCatService.updateById(foodMachineCat);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecat/delete")
    @RedisLock(value="fastfood:foodmachinecat:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineCatService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
