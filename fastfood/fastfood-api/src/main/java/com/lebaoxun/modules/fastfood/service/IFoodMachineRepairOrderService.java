package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineRepairOrderServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 维修派单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineRepairOrderServiceHystrix.class)
public interface IFoodMachineRepairOrderService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

