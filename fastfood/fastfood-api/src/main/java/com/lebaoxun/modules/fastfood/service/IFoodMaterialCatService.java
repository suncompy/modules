package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialCatEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMaterialCatServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 餐品原料分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMaterialCatServiceHystrix.class)
public interface IFoodMaterialCatService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmaterialcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);

    @RequestMapping("/fastfood/foodmaterialcat/select")
    ResponseMessage select();

    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmaterialcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmaterialcat/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialCatEntity foodMaterialCat);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmaterialcat/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialCatEntity foodMaterialCat);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmaterialcat/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

