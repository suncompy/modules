package com.lebaoxun.modules.account.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import com.lebaoxun.modules.account.service.hystrix.UserLevelPrivilegeServiceHystrix;

/**
 * 等级特权表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-10 15:54:53
 */

@FeignClient(value="account-service",fallback=UserLevelPrivilegeServiceHystrix.class)
public interface IUserLevelPrivilegeService {
	/**
     * 列表
     */
    @RequestMapping("/account/userlevelprivilege/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/account/userlevelprivilege/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/account/userlevelprivilege/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserLevelPrivilegeEntity userLevelPrivilege);

    /**
     * 修改
     */
    @RequestMapping("/account/userlevelprivilege/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserLevelPrivilegeEntity userLevelPrivilege);

    /**
     * 删除
     */
    @RequestMapping("/account/userlevelprivilege/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    @RequestMapping("/account/userlevelprivilege/findLevelByUserId")
    UserLevelPrivilegeEntity findLevelByUserId(@RequestParam("userId")Long userId,
    		@RequestParam("payLogType")String payLogType);
    
}

