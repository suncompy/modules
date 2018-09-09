package com.lebaoxun.modules.account.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.account.dao.UserDao;
import com.lebaoxun.modules.account.dao.UserLevelPrivilegeDao;
import com.lebaoxun.modules.account.dao.UserLogDao;
import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import com.lebaoxun.modules.account.service.UserLevelPrivilegeService;

@Service("userLevelPrivilegeService")
public class UserLevelPrivilegeServiceImpl extends ServiceImpl<UserLevelPrivilegeDao, UserLevelPrivilegeEntity> implements UserLevelPrivilegeService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private UserLogDao userLogDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserLevelPrivilegeEntity> page = this.selectPage(
                new Query<UserLevelPrivilegeEntity>(params).getPage(),
                new EntityWrapper<UserLevelPrivilegeEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public Integer findDisByUserId(Long userId, Integer level, String payLogType) {
		// TODO Auto-generated method stub
		Integer dis = 0;
		UserLevelPrivilegeEntity up = this.selectOne(new EntityWrapper<UserLevelPrivilegeEntity>().eq("level", level));
		if(up != null){
			Integer unit = up.getUnit() - 1;
			Calendar cal = Calendar.getInstance();
			Integer maxDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, maxDayOfMonth);
			String end = DateFormatUtils.format(cal, "yyyy-MM-dd")+" 59:59:59";
			
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - unit);
			String start = DateFormatUtils.format(cal, "yyyy-MM")+"-01 00:00:00";
			
			logger.debug("start={},end={}",start,end);
			BigDecimal totalFee = userLogDao.sumTradeMoneyByUserIdAndLogTypeAndTime(userId, payLogType, start, end);
			if(totalFee.compareTo(new BigDecimal(up.getPayAmount())) >= 0){
				dis = up.getDis();
			}
		}
		return dis;
	}

}
