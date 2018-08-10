package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodOrderChildsServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 订单明细表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodOrderChildsServiceHystrix.class)
public interface IFoodOrderChildsService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodorderchilds/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorderchilds/info/{orderProductId}")
    ResponseMessage info(@PathVariable("orderProductId") Long orderProductId);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorderchilds/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderChildsEntity foodOrderChilds);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorderchilds/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderChildsEntity foodOrderChilds);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorderchilds/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] orderProductIds);
    
}

