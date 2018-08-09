package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMaterialCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodMaterialCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 餐品原料分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMaterialCatController {
    @Autowired
    private FoodMaterialCatService foodMaterialCatService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmaterialcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMaterialCatService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmaterialcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMaterialCatEntity foodMaterialCat = foodMaterialCatService.selectById(id);
        return ResponseMessage.ok().put("foodMaterialCat", foodMaterialCat);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmaterialcat/save")
    @RedisLock(value="fastfood:foodmaterialcat:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialCatEntity foodMaterialCat){
		foodMaterialCatService.insert(foodMaterialCat);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmaterialcat/update")
    @RedisLock(value="fastfood:foodmaterialcat:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialCatEntity foodMaterialCat){
		foodMaterialCatService.updateById(foodMaterialCat);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmaterialcat/delete")
    @RedisLock(value="fastfood:foodmaterialcat:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMaterialCatService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
