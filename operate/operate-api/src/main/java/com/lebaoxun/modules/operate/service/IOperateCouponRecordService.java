package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateCouponRecordServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */

@FeignClient(value="operate-service",fallback=OperateCouponRecordServiceHystrix.class)
public interface IOperateCouponRecordService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operatecouponrecord/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecouponrecord/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecouponrecord/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponRecordEntity operateCouponRecord);

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecouponrecord/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponRecordEntity operateCouponRecord);

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecouponrecord/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

