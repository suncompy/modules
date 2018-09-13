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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineProductCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机餐品分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineProductCatController {
    @Autowired
    private FoodMachineProductCatService foodMachineProductCatService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineproductcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineProductCatService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineproductcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineProductCatEntity foodMachineProductCat = foodMachineProductCatService.selectById(id);
        return ResponseMessage.ok().put("foodMachineProductCat", foodMachineProductCat);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineproductcat/save")
    @RedisLock(value="fastfood:foodmachineproductcat:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProductCatEntity foodMachineProductCat){
        foodMachineProductCat.setCreateTime(new Date());
        foodMachineProductCat.setCreateBy(adminId);
        foodMachineProductCat.setUpdateBy(adminId);
        foodMachineProductCat.setUpdateTime(new Date());
		foodMachineProductCatService.insert(foodMachineProductCat);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineproductcat/update")
    @RedisLock(value="fastfood:foodmachineproductcat:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProductCatEntity foodMachineProductCat){
        foodMachineProductCat.setUpdateBy(adminId);
        foodMachineProductCat.setUpdateTime(new Date());
		foodMachineProductCatService.updateById(foodMachineProductCat);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineproductcat/delete")
    @RedisLock(value="fastfood:foodmachineproductcat:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineProductCatService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    @RequestMapping("/fastfood/foodmachineproductcat/findCatByMacId")
    ResponseMessage findCatByMacId(@RequestParam("macId")Integer macId){
    	return ResponseMessage.ok(foodMachineProductCatService.selectList(new EntityWrapper<FoodMachineProductCatEntity>().eq("mac_id", macId)));
    }
}
