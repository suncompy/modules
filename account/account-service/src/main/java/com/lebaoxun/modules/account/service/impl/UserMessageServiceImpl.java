package com.lebaoxun.modules.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.modules.account.dao.UserMessageDao;
import com.lebaoxun.modules.account.entity.UserEntity;
import com.lebaoxun.modules.account.entity.UserMessageEntity;
import com.lebaoxun.modules.account.service.UserMessageService;
import com.lebaoxun.modules.account.service.UserService;


@Service("userMessageService")
public class UserMessageServiceImpl extends ServiceImpl<UserMessageDao, UserMessageEntity> implements UserMessageService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String userId = (String)params.get("user_id");
    	String mobile = (String)params.get("mobile");
    	String type = (String)params.get("type");
        Page<UserMessageEntity> page = this.selectPage(
                new Query<UserMessageEntity>(params).getPage(),
                new EntityWrapper<UserMessageEntity>()
                .eq(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId), "user_id", userId)
                .eq(StringUtils.isNotBlank(mobile), "mobile", mobile)
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
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean insert(UserMessageEntity entity) {
		// TODO Auto-generated method stub
		String mobile = entity.getMobile();
		if(StringUtils.isBlank(mobile)){
			throw new I18nMessageException("500","手机号不允许为空");
		}
		logger.debug("mobile={}",mobile);
		String mobiles[] = mobile.split("\\,");
		for(String m : mobiles){
			UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("account", m).or("mobile", m));
			if(user == null){
				throw new I18nMessageException("500","用户’"+m+"‘不存在");
			}
			UserMessageEntity ume = new UserMessageEntity();
			ume.setContent(entity.getContent());
			ume.setTitle(entity.getTitle());
			ume.setMobile(m);
			ume.setUserId(user.getUserId());
			ume.setCreateBy(entity.getCreateBy());
			ume.setDelFlag(0);
			ume.setCreateTime(new Date());
			ume.setType(1);
			this.baseMapper.insert(ume);
		}
		return true;
	}

}
