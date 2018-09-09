package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineAdvanceTimeServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * 取餐机预定时间配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineAdvanceTimeServiceHystrix.class)
public interface IFoodMachineAdvanceTimeService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAdvanceTimeEntity foodMachineAdvanceTime);

    @RequestMapping("/fastfood/foodmachineadvancetime/batchSave")
    ResponseMessage batchSave(@RequestParam("adminId")Long adminId,@RequestBody List<FoodMachineAdvanceTimeEntity> advanceTimeList);
    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAdvanceTimeEntity foodMachineAdvanceTime);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    @RequestMapping("/fastfood/foodmachineadvancetime/findAdvanceTimeByMacId")
    ResponseMessage findAdvanceTimeByMacId(@RequestParam("macId")Integer macId);

    @RequestMapping("/fastfood/foodmachineadvancetime/findPreOrderAndProByMacId")
    ResponseMessage findPreOrderAndProByMacId(@RequestParam("macId")Integer macId);
}

