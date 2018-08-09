package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateCouponMacRcrtEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateCouponMacRcrtServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 取餐机关联表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */

@FeignClient(value="operate-service",fallback=OperateCouponMacRcrtServiceHystrix.class)
public interface IOperateCouponMacRcrtService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operatecouponmacrcrt/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecouponmacrcrt/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecouponmacrcrt/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponMacRcrtEntity operateCouponMacRcrt);

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecouponmacrcrt/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponMacRcrtEntity operateCouponMacRcrt);

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecouponmacrcrt/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

