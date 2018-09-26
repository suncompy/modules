package com.lebaoxun.modules.manager.dao;

import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.manager.entity.SysUserLogEntity;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 管理员日志表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-26 12:04:12
 */
@Mapper
public interface SysUserLogDao extends BaseMapper<SysUserLogEntity> {
	List<Map<String,Object>> queryAllLogType();
}
