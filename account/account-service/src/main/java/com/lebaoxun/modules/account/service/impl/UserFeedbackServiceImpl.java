package com.lebaoxun.modules.account.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.account.dao.UserFeedbackDao;
import com.lebaoxun.modules.account.entity.UserFeedbackEntity;
import com.lebaoxun.modules.account.service.UserFeedbackService;


@Service("userFeedbackService")
public class UserFeedbackServiceImpl extends ServiceImpl<UserFeedbackDao, UserFeedbackEntity> implements UserFeedbackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserFeedbackEntity> page = this.selectPage(
                new Query<UserFeedbackEntity>(params).getPage(),
                new EntityWrapper<UserFeedbackEntity>()
        );

        return new PageUtils(page);
    }

}
