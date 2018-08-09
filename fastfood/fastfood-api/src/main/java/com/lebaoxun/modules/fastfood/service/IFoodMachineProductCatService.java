package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineProductCatServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机餐品分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineProductCatServiceHystrix.class)
public interface IFoodMachineProductCatService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineproductcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineproductcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineproductcat/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProductCatEntity foodMachineProductCat);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineproductcat/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProductCatEntity foodMachineProductCat);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineproductcat/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

