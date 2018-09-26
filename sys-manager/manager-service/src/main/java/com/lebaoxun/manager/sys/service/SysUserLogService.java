package com.lebaoxun.manager.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.manager.entity.SysUserLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 管理员日志表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-26 12:04:12
 */
public interface SysUserLogService extends IService<SysUserLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<Map<String,Object>> queryAllLogType();
}

