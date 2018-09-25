package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineErrorLogEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineErrorLogServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 终端机器错误日志表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-23 19:35:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineErrorLogServiceHystrix.class)
public interface IFoodMachineErrorLogService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineErrorLogEntity foodMachineErrorLog);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineErrorLogEntity foodMachineErrorLog);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

