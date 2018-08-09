package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineCatServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:11
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineCatServiceHystrix.class)
public interface IFoodMachineCatService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecat/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatEntity foodMachineCat);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecat/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatEntity foodMachineCat);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecat/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

