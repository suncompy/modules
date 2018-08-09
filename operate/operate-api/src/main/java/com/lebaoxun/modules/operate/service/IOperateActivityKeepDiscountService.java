package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateActivityKeepDiscountServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 连续折扣
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */

@FeignClient(value="operate-service",fallback=OperateActivityKeepDiscountServiceHystrix.class)
public interface IOperateActivityKeepDiscountService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityKeepDiscountEntity operateActivityKeepDiscount);

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityKeepDiscountEntity operateActivityKeepDiscount);

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

