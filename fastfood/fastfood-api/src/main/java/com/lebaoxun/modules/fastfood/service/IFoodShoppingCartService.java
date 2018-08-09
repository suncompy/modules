package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodShoppingCartServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 购物车表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 18:47:10
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
    
}

