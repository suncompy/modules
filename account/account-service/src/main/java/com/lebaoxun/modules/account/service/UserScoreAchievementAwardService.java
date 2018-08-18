package com.lebaoxun.modules.account.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.account.entity.UserScoreAchievementAwardEntity;

import java.util.Map;

/**
 * 用户任务表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
public interface UserScoreAchievementAwardService extends IService<UserScoreAchievementAwardEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 根据玩家ID + 操作行为，发放玩家任务积分
     * @param userId
     * @param logId 操作日志ID
     */
    void awardUserScore(long userId, long logId);
}

