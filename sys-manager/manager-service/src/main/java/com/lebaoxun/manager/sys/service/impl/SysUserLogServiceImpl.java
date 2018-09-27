package com.lebaoxun.manager.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.manager.sys.service.SysUserLogService;
import com.lebaoxun.modules.manager.dao.SysUserLogDao;
import com.lebaoxun.modules.manager.entity.SysUserLogEntity;


@Service("sysUserLogService")
public class SysUserLogServiceImpl extends ServiceImpl<SysUserLogDao, SysUserLogEntity> implements SysUserLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String logType = (String)params.get("logType"),
    			adminId = (String)params.get("adminId"),
    			mobile = (String)params.get("mobile"),
    			refId = (String)params.get("refId");
        Page<SysUserLogEntity> page = this.selectPage(
                new Query<SysUserLogEntity>(params).getPage(),
                new EntityWrapper<SysUserLogEntity>()
                .eq(StringUtils.isNotBlank(logType),"log_type", logType)
                .eq(StringUtils.isNotBlank(mobile),"mobile", mobile)
                .eq(StringUtils.isNotBlank(refId),"ref_id", refId)
                .eq(StringUtils.isNotBlank(adminId) && StringUtils.isInteger(adminId), "admin_id", adminId)
                .orderBy("log_time", false)
        );
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> queryAllLogType() {
    	// TODO Auto-generated method stub
    	return this.baseMapper.queryAllLogType();
    }
}
