package com.lebaoxun.modules.fastfood.service;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;

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
 * @date 2018-08-10 16:34:01
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
    
    
    /**
     * 根据机器查询产品
     * @param macId
     * @param productCatId
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaisle/findProductByMacIdAndProductCatId")
    ResponseMessage findProductByMacIdAndProductCatId(
    		@RequestParam("macId")Integer macId, 
    		@RequestParam(value="productCatId",required=false)Integer productCatId);

    @RequestMapping("/fastfood/foodmachineaisle/findMachineAisleListByMacId")
    ResponseMessage findMachineAisleListByMacId(@RequestParam("macId")Integer macId);

    /**
     * 机器列表关联产品
     * @param adminId
     * @param foodMachineAisle
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaisle/refProductAndType")
    ResponseMessage refProductAndType(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRefAisleEntity foodMachineAisle);
    
    /**
	 * 每周菜谱关联 机器产品列表
	 * @param macId
	 * @param week
	 * @return
	 */
    @RequestMapping("/fastfood/foodmachineaisle/findProductByMacIdAndWeek")
    ResponseMessage findProductByMacIdAndWeek(@RequestParam("macId") Integer macId, 
    		@RequestParam("week")Integer week);
}

