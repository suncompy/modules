package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineAddStockOrderServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineAddStockOrderServiceHystrix.class)
public interface IFoodMachineAddStockOrderService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockOrderEntity foodMachineAddStockOrder);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockOrderEntity foodMachineAddStockOrder);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

