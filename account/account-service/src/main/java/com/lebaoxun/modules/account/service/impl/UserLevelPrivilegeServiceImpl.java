package com.lebaoxun.modules.account.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.account.dao.UserLevelPrivilegeDao;
import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import com.lebaoxun.modules.account.service.UserLevelPrivilegeService;


@Service("userLevelPrivilegeService")
public class UserLevelPrivilegeServiceImpl extends ServiceImpl<UserLevelPrivilegeDao, UserLevelPrivilegeEntity> implements UserLevelPrivilegeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserLevelPrivilegeEntity> page = this.selectPage(
                new Query<UserLevelPrivilegeEntity>(params).getPage(),
                new EntityWrapper<UserLevelPrivilegeEntity>()
        );

        return new PageUtils(page);
    }

}
