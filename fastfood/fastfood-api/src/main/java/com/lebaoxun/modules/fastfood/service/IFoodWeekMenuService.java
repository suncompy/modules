package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodWeekMenuEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodWeekMenuServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 每周菜谱
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodWeekMenuServiceHystrix.class)
public interface IFoodWeekMenuService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodweekmenu/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodweekmenu/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodweekmenu/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodWeekMenuEntity foodWeekMenu);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodweekmenu/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodWeekMenuEntity foodWeekMenu);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodweekmenu/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

