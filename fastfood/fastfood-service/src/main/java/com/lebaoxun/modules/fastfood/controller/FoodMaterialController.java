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

import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.service.FoodMaterialService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 餐品原料表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMaterialController {
    @Autowired
    private FoodMaterialService foodMaterialService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmaterial/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMaterialService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmaterial/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMaterialEntity foodMaterial = foodMaterialService.selectById(id);
        return ResponseMessage.ok().put("foodMaterial", foodMaterial);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmaterial/save")
    @RedisLock(value="fastfood:foodmaterial:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialEntity foodMaterial){
    	foodMaterial.setCreateBy(adminId);
    	foodMaterial.setCreateTime(new Date());
		foodMaterialService.insert(foodMaterial);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmaterial/update")
    @RedisLock(value="fastfood:foodmaterial:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialEntity foodMaterial){
    	foodMaterial.setUpdateBy(adminId);
    	foodMaterial.setUpdateTime(new Date());
		foodMaterialService.updateById(foodMaterial);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmaterial/delete")
    @RedisLock(value="fastfood:foodmaterial:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMaterialService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
