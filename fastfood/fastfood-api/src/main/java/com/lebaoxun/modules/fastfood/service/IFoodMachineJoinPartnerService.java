package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineJoinPartnerEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineJoinPartnerServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 加盟商
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineJoinPartnerServiceHystrix.class)
public interface IFoodMachineJoinPartnerService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineJoinPartnerEntity foodMachineJoinPartner);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineJoinPartnerEntity foodMachineJoinPartner);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

