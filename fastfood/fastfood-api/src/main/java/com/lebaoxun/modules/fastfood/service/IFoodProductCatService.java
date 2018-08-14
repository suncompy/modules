package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodProductCatServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 餐品分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodProductCatServiceHystrix.class)
public interface IFoodProductCatService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodproductcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodproductcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodproductcat/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodProductCatEntity foodProductCat);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodproductcat/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodProductCatEntity foodProductCat);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodproductcat/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    /**
     * 查询所有分类
     */
    @RequestMapping("/fastfood/foodproductcat/queryAll")
    ResponseMessage queryAll();
    
    /**
     * 查询餐品分类下所有原料
     */
    @RequestMapping("/fastfood/foodproductcat/queryFoodMaterialById")
    ResponseMessage queryFoodMaterialById(@RequestParam("id")Integer id);
    
}

