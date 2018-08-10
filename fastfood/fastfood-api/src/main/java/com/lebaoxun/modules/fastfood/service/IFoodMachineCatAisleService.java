package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineCatAisleServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机分类通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineCatAisleServiceHystrix.class)
public interface IFoodMachineCatAisleService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecataisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecataisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecataisle/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatAisleEntity foodMachineCatAisle);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecataisle/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatAisleEntity foodMachineCatAisle);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecataisle/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

