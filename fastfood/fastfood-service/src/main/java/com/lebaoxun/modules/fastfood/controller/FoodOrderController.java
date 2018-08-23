package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.modules.fastfood.service.FoodOrderChildsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
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

import javax.annotation.Resource;


/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderController {
    @Autowired
    private FoodOrderService foodOrderService;

    @Autowired
    private FoodOrderChildsService foodOrderChildsService;

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
    
    /**
     * 普通下单
     * @param orders
     * @return
     */
    @RequestMapping("/fastfood/foodorder/createOrder")
    @RedisLock(value="fastfood:foodorder:createOrder:lock:#arg0")
    ResponseMessage createOrder(
    		@RequestParam("macId") Long macId,
    		@RequestParam("userId") Long userId,
    		@RequestBody FoodOrderEntity order){
    	return ResponseMessage.ok(foodOrderService.createOrder(userId, order));
    }
    
    /**
     * 计算订单金额，并验证产品是否有效
     * @param order
     * @return
     */
    @RequestMapping("/fastfood/foodorder/calCheckTotalFee")
    ResponseMessage calCheckTotalFee(@RequestBody FoodOrderEntity order){
    	return ResponseMessage.ok(foodOrderService.calCheckTotalFee(order));
    }

    /**
     * 扫码查询订单信息
     * @param orderId
     * @return
     */
    @RequestMapping("/fastfood/getSweeptCodeOrderInfo")
    ResponseMessage getSweeptCodeOrderInfo(@RequestParam("orderId") String orderId) {
        Map<String,Object> orderMap=foodOrderService.getSweeptCodeOrderInfo(orderId);
        if (orderMap==null||orderMap.size()==0)
            return ResponseMessage.error("00002","订单不存！");
        List<Map<String,Object>> orderChildList=foodOrderChildsService.getSweeptCodeOrderChildsInfo(orderId);
        orderMap.put("orderLines",orderChildList);
        return ResponseMessage.ok(orderMap);
    }

    /**
     * 取餐订单入队
     * @param orderId
     * @return
     */
    @RequestMapping("/take_food/push_order")
    ResponseMessage pushOrder(@RequestParam("orderId") Long orderId){
        return foodOrderService.pushOrder(orderId);
    }

    /**
     * 取餐回调接口
     * @param orderId
     * @return
     */
    @RequestMapping("/take_food/takeFoodCallback")
    ResponseMessage takeFoodCallback(
            @RequestParam("orderId") String orderId){
        return foodOrderService.takeFoodCallback(orderId);
    }
}
