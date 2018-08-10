package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodProductServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 餐品表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodProductServiceHystrix.class)
public interface IFoodProductService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodproduct/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodproduct/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodproduct/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodProductEntity foodProduct);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodproduct/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodProductEntity foodProduct);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodproduct/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

