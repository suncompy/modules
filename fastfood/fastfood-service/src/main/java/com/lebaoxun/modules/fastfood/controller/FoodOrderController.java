package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 18:47:10
 */
@RestController
public class FoodOrderController {
    @Autowired
    private FoodOrderService foodOrderService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		FoodOrderEntity foodOrder = foodOrderService.selectById(id);
        return ResponseMessage.ok().put("foodOrder", foodOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorder/save")
    @RedisLock(value="fastfood:foodorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder){
		foodOrderService.insert(foodOrder);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorder/update")
    @RedisLock(value="fastfood:foodorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder){
		foodOrderService.updateById(foodOrder);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorder/delete")
    @RedisLock(value="fastfood:foodorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		foodOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
