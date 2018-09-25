package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineAddStockHeadServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机进货派单主表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-25 16:41:14
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineAddStockHeadServiceHystrix.class)
public interface IFoodMachineAddStockHeadService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockHeadEntity foodMachineAddStockHead);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockHeadEntity foodMachineAddStockHead);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaddstockhead/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

