package com.lebaoxun.modules.account.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.account.entity.UserInvoiceEntity;
import com.lebaoxun.modules.account.service.hystrix.UserInvoiceServiceHystrix;

/**
 * 发票信息
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 14:13:24
 */

@FeignClient(value="account-service",fallback=UserInvoiceServiceHystrix.class)
public interface IUserInvoiceService {
	/**
     * 列表
     */
    @RequestMapping("/account/userinvoice/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/account/userinvoice/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/account/userinvoice/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserInvoiceEntity userInvoice);

    /**
     * 修改
     */
    @RequestMapping("/account/userinvoice/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserInvoiceEntity userInvoice);

    /**
     * 删除
     */
    @RequestMapping("/account/userinvoice/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    @RequestMapping("/account/userinvoice/findByUserId")
    ResponseMessage findByUserId(@RequestParam("userId")Long userId);
    
    @RequestMapping("/account/userinvoice/deleteByUserId")
    ResponseMessage deleteByUserId(@RequestParam("userId")Long userId,@RequestParam("id") Integer id);
}

