package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.FoodShoppingCartService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 购物车表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodShoppingCartController {
    @Autowired
    private FoodShoppingCartService foodShoppingCartService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodshoppingcart/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodShoppingCartService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodshoppingcart/info/{cartId}")
    ResponseMessage info(@PathVariable("cartId") Long cartId){
		FoodShoppingCartEntity foodShoppingCart = foodShoppingCartService.selectById(cartId);
        return ResponseMessage.ok().put("foodShoppingCart", foodShoppingCart);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodshoppingcart/save")
    @RedisLock(value="fastfood:foodshoppingcart:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodShoppingCartEntity foodShoppingCart){
		foodShoppingCartService.insert(foodShoppingCart);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodshoppingcart/update")
    @RedisLock(value="fastfood:foodshoppingcart:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodShoppingCartEntity foodShoppingCart){
		foodShoppingCartService.updateById(foodShoppingCart);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodshoppingcart/delete")
    @RedisLock(value="fastfood:foodshoppingcart:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] cartIds){
		foodShoppingCartService.deleteBatchIds(Arrays.asList(cartIds));
        return ResponseMessage.ok();
    }
    
    /**
     * 添加购物车
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/add")
    @RedisLock(value="fastfood:foodshoppingcart:lock:#arg0")
    ResponseMessage add(
    		@RequestParam("userId")Long userId,
    		@RequestParam("aisleId")Integer aisleId,
    		@RequestParam("macId")Integer macId,
    		@RequestParam("productId")Integer productId,
    		@RequestParam("checkStatus")Integer checkStatus,
    		@RequestParam("buyNumber")Integer buyNumber){
    	return ResponseMessage.ok(foodShoppingCartService.add(userId, aisleId, macId, productId, checkStatus, buyNumber));
    }
    
    /**
     * 设置数量
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/set")
    @RedisLock(value="fastfood:foodshoppingcart:lock:#arg0")
    ResponseMessage set(
    		@RequestParam("userId")Long userId,
    		@RequestParam("cartId")Long cartId,
    		@RequestParam("checkStatus")Integer checkStatus,
    		@RequestParam("buyNumber")Integer buyNumber){
    	return ResponseMessage.ok(foodShoppingCartService.set(userId, cartId, checkStatus, buyNumber));
    }
    
    /**
     * 删除
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/remove")
    @RedisLock(value="fastfood:foodshoppingcart:lock:#arg0")
    ResponseMessage remove(
    		@RequestParam("userId")Long userId,
    		@RequestParam("cartId")Long cartId){
    	foodShoppingCartService.remove(userId, cartId);
    	return ResponseMessage.ok();
    }
    
    /**
     * 购物车列表
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/list")
    ResponseMessage findByUser(@RequestParam("userId")Long userId){
    	return ResponseMessage.ok(foodShoppingCartService.findByUser(userId));
    }
    

}
