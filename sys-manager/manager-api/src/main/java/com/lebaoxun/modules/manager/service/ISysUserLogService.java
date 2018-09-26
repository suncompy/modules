package com.lebaoxun.modules.manager.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.manager.service.hystrix.SysUserLogServiceHystrix;

/**
 * 管理员日志表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-26 12:04:12
 */

@FeignClient(value="manager-service",fallback=SysUserLogServiceHystrix.class)
public interface ISysUserLogService {
	/**
     * 列表
     */
    @RequestMapping("/manager/sysuserlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);

    /**
     * 查询管理员日志
     * @param userId
     * @param size
     * @param offset
     * @return
     */
    @RequestMapping("/manager/sysuserlog/findLogByAdminId")
    ResponseMessage findLogByUserId(@RequestParam(value="adminId") Long adminId,
    		@RequestParam(value="logType",required=false) String logType);
    
    @RequestMapping("/manager/sysuserlog/queryAllLogType")
    ResponseMessage queryAllLogType();
}

