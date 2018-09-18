package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerRefEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachinePartnerRefServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 机器合作商关联表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */

@FeignClient(value="fastfood-service",fallback=FoodMachinePartnerRefServiceHystrix.class)
public interface IFoodMachinePartnerRefService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerRefEntity foodMachinePartnerRef);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerRefEntity foodMachinePartnerRef);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

