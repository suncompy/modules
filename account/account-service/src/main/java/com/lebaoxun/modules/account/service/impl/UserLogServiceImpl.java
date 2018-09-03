package com.lebaoxun.modules.account.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lebaoxun.commons.utils.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.account.dao.UserDao;
import com.lebaoxun.modules.account.dao.UserLogDao;
import com.lebaoxun.modules.account.entity.UserLogEntity;
import com.lebaoxun.modules.account.service.UserLogService;
import com.lebaoxun.soa.core.redis.IRedisSorted;


@Service("userLogService")
public class UserLogServiceImpl extends ServiceImpl<UserLogDao, UserLogEntity> implements UserLogService {
	
	@Resource
	private IRedisSorted redisSorted;
	
	@Resource
	private UserDao userDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String userId = (String)params.get("userId");
    	String account = (String)params.get("account");
    	String type = (String)params.get("type");
        Page<UserLogEntity> page = this.selectPage(
                new Query<UserLogEntity>(params).getPage(),
                new EntityWrapper<UserLogEntity>()
                .eq(StringUtils.isNotBlank(userId) && StringUtils.isNumeric(userId), "user_id", userId)
                .eq(StringUtils.isNotBlank(account), "account", account)
                .eq(StringUtils.isNotBlank(type), "log_type", type)
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> queryAllLogType() {
    	// TODO Auto-generated method stub
    	return this.baseMapper.queryAllLogType();
    }

	@Override
	public List<UserLogEntity> queryAccountLogByUserId(Long userId,
			String flag, String logType,
			Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return this.baseMapper.queryAccountLogByUserId(userId, flag, logType, size, offset);
	}

	@Override
	public void zRange(Long userId, String logType, String time) {
		// TODO Auto-generated method stub
		String key = "account:tradeMoney:"+logType+":ranks"+":"+time;
		BigDecimal total = this.baseMapper.sumTradeMoneyByUser(userId, time, logType);
		redisSorted.zAdd(key, total.doubleValue(), userId);
	}

	@Override
	public Map<String, Object> zRank(Long userId, String logType, String time) {
		// TODO Auto-generated method stub
		String key = "account:tradeMoney:"+logType+":ranks"+":"+time;
		if(!redisSorted.exists(key)){
			return null;
		}
		Long rank = redisSorted.zRank(key, userId),
				size = redisSorted.zSize(key),
					score = redisSorted.zScore(key,userId).longValue();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("score", score);
		result.put("rank", rank);
		result.put("size", size);
		return result;
	}
	
}
