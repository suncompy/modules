package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderChildsService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 订单明细表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderChildsController {
    @Autowired
    private FoodOrderChildsService foodOrderChildsService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorderchilds/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderChildsService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorderchilds/info/{orderProductId}")
    ResponseMessage info(@PathVariable("orderProductId") Long orderProductId){
		FoodOrderChildsEntity foodOrderChilds = foodOrderChildsService.selectById(orderProductId);
        return ResponseMessage.ok().put("foodOrderChilds", foodOrderChilds);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorderchilds/save")
    @RedisLock(value="fastfood:foodorderchilds:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderChildsEntity foodOrderChilds){
		foodOrderChildsService.insert(foodOrderChilds);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorderchilds/update")
    @RedisLock(value="fastfood:foodorderchilds:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderChildsEntity foodOrderChilds){
		foodOrderChildsService.updateById(foodOrderChilds);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorderchilds/delete")
    @RedisLock(value="fastfood:foodorderchilds:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] orderProductIds){
		foodOrderChildsService.deleteBatchIds(Arrays.asList(orderProductIds));
        return ResponseMessage.ok();
    }

}
