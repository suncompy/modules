package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateCouponEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateCouponServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 优惠券
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:33
 */

@FeignClient(value="operate-service",fallback=OperateCouponServiceHystrix.class)
public interface IOperateCouponService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operatecoupon/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecoupon/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecoupon/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponEntity operateCoupon);

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecoupon/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponEntity operateCoupon);

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecoupon/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

