package com.lebaoxun.modules.account.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.account.dao.UserMessageDao;
import com.lebaoxun.modules.account.entity.UserMessageEntity;
import com.lebaoxun.modules.account.service.UserMessageService;


@Service("userMessageService")
public class UserMessageServiceImpl extends ServiceImpl<UserMessageDao, UserMessageEntity> implements UserMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String userId = (String)params.get("user_id");
    	String type = (String)params.get("type");
        Page<UserMessageEntity> page = this.selectPage(
                new Query<UserMessageEntity>(params).getPage(),
                new EntityWrapper<UserMessageEntity>()
                .eq(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId), "user_id", userId)
                .eq(StringUtils.isNotBlank(type), "type", type)
        );

        return new PageUtils(page);
    }

	@Override
	public List<UserMessageEntity> findInformByUserId(Long userId,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return this.baseMapper.findInformByUserId(userId, size, offset);
	}

	@Override
	public UserMessageEntity findOneInformByUserId(Long userId, long id) {
		// TODO Auto-generated method stub
		return this.baseMapper.findOneInformByUserId(userId, id);
	}

}
