package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMaterialServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 餐品原料表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMaterialServiceHystrix.class)
public interface IFoodMaterialService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmaterial/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmaterial/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmaterial/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialEntity foodMaterial);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmaterial/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMaterialEntity foodMaterial);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmaterial/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

