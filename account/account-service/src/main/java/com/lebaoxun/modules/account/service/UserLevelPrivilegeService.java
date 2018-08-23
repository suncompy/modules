package com.lebaoxun.modules.account.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;

import java.util.Map;

/**
 * 等级特权表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
public interface UserLevelPrivilegeService extends IService<UserLevelPrivilegeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    Integer findDisByUserId(Long userId,Integer level,String payLogType);
}

