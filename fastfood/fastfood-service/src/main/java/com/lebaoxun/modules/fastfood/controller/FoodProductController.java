package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import com.lebaoxun.modules.fastfood.service.FoodProductService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 餐品表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodProductController {
    @Autowired
    private FoodProductService foodProductService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodproduct/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodProductService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodproduct/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodProductEntity foodProduct = foodProductService.selectById(id);
        return ResponseMessage.ok().put("foodProduct", foodProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodproduct/save")
    @RedisLock(value="fastfood:foodproduct:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodProductEntity foodProduct){
		foodProductService.insert(foodProduct);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodproduct/update")
    @RedisLock(value="fastfood:foodproduct:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodProductEntity foodProduct){
		foodProductService.updateById(foodProduct);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodproduct/delete")
    @RedisLock(value="fastfood:foodproduct:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodProductService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
