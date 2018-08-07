package com.lebaoxun.modules.account.dao;

import com.lebaoxun.modules.account.entity.UserFeedbackEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 用户反馈表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
@Mapper
public interface UserFeedbackDao extends BaseMapper<UserFeedbackEntity> {
	
}
