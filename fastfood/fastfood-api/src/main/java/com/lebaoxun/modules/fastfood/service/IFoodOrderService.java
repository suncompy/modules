package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodOrderServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodOrderServiceHystrix.class)
public interface IFoodOrderService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorder/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorder/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorder/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids);

    /**
     * 扫码查询订单信息
     * @param orderId
     * @return
     */
    @RequestMapping("/fastfood/getSweeptCodeOrderInfo")
    ResponseMessage getSweeptCodeOrderInfo(@RequestParam("orderId") String orderId);
    
}

