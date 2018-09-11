package com.lebaoxun.modules.operate.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateCouponRecordServiceHystrix;

/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
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

    /**
	 * 查询用户未过期，未使用的优惠券
	 * @param userId
	 * @return
	 */
    @RequestMapping("/operate/operatecouponrecord/findByUserId")
    ResponseMessage findByUserId(@RequestParam("userId")Long userId,
    		@RequestParam(value="macId",required=false)Integer macId,
    		@RequestParam(value="use",required=false)Integer use,
    		@RequestParam(value="flag",required=false)Integer flag,
    		@RequestParam(value="size",required=false)Integer size,
    		@RequestParam(value="offset",required=false)Integer offset);
    
    /**
     * 领取优惠券
     * @param userId
     * @param macId
     * @param couponId
     * @return
     */
    @RequestMapping("/operate/operatecouponrecord/getCouponByCouponId")
    ResponseMessage getCouponByCouponId(@RequestParam("userId")Long userId,
    		@RequestParam("macId")Integer macId,
    		@RequestParam("couponId")Integer couponId);
}

