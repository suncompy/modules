package com.lebaoxun.manager.sys.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.manager.sys.service.SysUserLogService;
import com.lebaoxun.modules.manager.entity.SysUserLogEntity;


/**
 * 管理员日志表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-26 12:04:12
 */
@RestController
public class SysUserLogController {
    @Autowired
    private SysUserLogService sysUserLogService;

    /**
     * 列表
     */
    @RequestMapping("/manager/sysuserlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserLogService.queryPage(params);
        return ResponseMessage.ok(page);
    }

    /**
     * 查询管理员日志
     * @param userId
     * @param size
     * @param offset
     * @return
     */
    @RequestMapping("/manager/sysuserlog/findLogByAdminId")
    ResponseMessage findLogByUserId(@RequestParam(value="adminId") Long adminId,
    		@RequestParam(value="logType",required=false) String logType){
    	return ResponseMessage.ok(sysUserLogService.selectList(new EntityWrapper<SysUserLogEntity>()
                .eq(StringUtils.isNotBlank(logType),"log_type", logType)
                .eq(adminId != null, "admin_id", adminId)
                .orderBy("log_time", false)));
    }

    @RequestMapping("/manager/sysuserlog/queryAllLogType")
    ResponseMessage queryAllLogType(){
    	return ResponseMessage.ok(sysUserLogService.queryAllLogType());
    }
}
