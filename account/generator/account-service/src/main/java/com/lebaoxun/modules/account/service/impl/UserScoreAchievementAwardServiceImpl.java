package com.lebaoxun.modules.account.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.account.dao.UserScoreAchievementAwardDao;
import com.lebaoxun.modules.account.entity.UserScoreAchievementAwardEntity;
import com.lebaoxun.modules.account.service.UserScoreAchievementAwardService;


@Service("userScoreAchievementAwardService")
public class UserScoreAchievementAwardServiceImpl extends ServiceImpl<UserScoreAchievementAwardDao, UserScoreAchievementAwardEntity> implements UserScoreAchievementAwardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserScoreAchievementAwardEntity> page = this.selectPage(
                new Query<UserScoreAchievementAwardEntity>(params).getPage(),
                new EntityWrapper<UserScoreAchievementAwardEntity>()
        );

        return new PageUtils(page);
    }

}
