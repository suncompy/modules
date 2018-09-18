package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachinePartnerServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 合作公司
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */

@FeignClient(value="fastfood-service",fallback=FoodMachinePartnerServiceHystrix.class)
public interface IFoodMachinePartnerService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinepartner/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinepartner/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinepartner/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerEntity foodMachinePartner);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinepartner/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerEntity foodMachinePartner);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinepartner/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

