package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineAisleServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineAisleServiceHystrix.class)
public interface IFoodMachineAisleService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaisle/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAisleEntity foodMachineAisle);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaisle/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAisleEntity foodMachineAisle);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaisle/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

