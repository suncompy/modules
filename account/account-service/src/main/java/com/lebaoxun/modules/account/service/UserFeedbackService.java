package com.lebaoxun.modules.account.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.account.entity.UserFeedbackEntity;

import java.util.Map;

/**
 * 用户反馈表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
public interface UserFeedbackService extends IService<UserFeedbackEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

