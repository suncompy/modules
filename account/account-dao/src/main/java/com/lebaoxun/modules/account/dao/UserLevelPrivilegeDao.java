package com.lebaoxun.modules.account.dao;

import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 等级特权表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-10 15:54:53
 */
@Mapper
public interface UserLevelPrivilegeDao extends BaseMapper<UserLevelPrivilegeEntity> {
	
}
