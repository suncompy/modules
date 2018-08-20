package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodShoppingCartServiceHystrix;

/**
 * 购物车表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodShoppingCartServiceHystrix.class)
public interface IFoodShoppingCartService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodshoppingcart/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodshoppingcart/info/{cartId}")
    ResponseMessage info(@PathVariable("cartId") Long cartId);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodshoppingcart/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodShoppingCartEntity foodShoppingCart);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodshoppingcart/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodShoppingCartEntity foodShoppingCart);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodshoppingcart/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] cartIds);
    
    
    /**
     * 添加购物车
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/add")
    ResponseMessage add(
    		@RequestParam("userId")Long userId,
    		@RequestParam("aisleId")Integer aisleId,
    		@RequestParam("macId")Integer macId,
    		@RequestParam("productId")Integer productId,
    		@RequestParam("checkStatus")Integer checkStatus,
    		@RequestParam("buyNumber")Integer buyNumber);
    
    /**
     * 设置数量
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/set")
    ResponseMessage set(
    		@RequestParam("userId")Long userId,
    		@RequestParam("cartId")Long cartId,
    		@RequestParam("checkStatus")Integer checkStatus,
    		@RequestParam("buyNumber")Integer buyNumber);
    
    /**
     * 删除
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/remove")
    ResponseMessage remove(
    		@RequestParam("userId")Long userId,
    		@RequestParam("cartId")Long cartId);
    
    /**
     * 购物车列表
     * @param userId
     * @param macId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodshoppingcart/list")
    ResponseMessage findByUser(@RequestParam("userId")Long userId);
    
}

