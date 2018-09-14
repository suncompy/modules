package com.lebaoxun.modules.account.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.account.dao.UserLogDao;
import com.lebaoxun.modules.account.dao.UserScoreAchievementAwardDao;
import com.lebaoxun.modules.account.em.UserLogAction;
import com.lebaoxun.modules.account.entity.UserEntity;
import com.lebaoxun.modules.account.entity.UserLogEntity;
import com.lebaoxun.modules.account.entity.UserScoreAchievementAwardEntity;
import com.lebaoxun.modules.account.service.UserScoreAchievementAwardService;
import com.lebaoxun.modules.account.service.UserService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;


@Service("userScoreAchievementAwardService")
public class UserScoreAchievementAwardServiceImpl extends ServiceImpl<UserScoreAchievementAwardDao, UserScoreAchievementAwardEntity> implements UserScoreAchievementAwardService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserLogDao userLogDao;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserScoreAchievementAwardEntity> page = this.selectPage(
                new Query<UserScoreAchievementAwardEntity>(params).getPage(),
                new EntityWrapper<UserScoreAchievementAwardEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void awardUserScore(long userId, long logId) {
		UserLogEntity log = userLogDao.selectById(logId);
		String action = log.getLogType();
		// 用action获取任务集合
		List<UserScoreAchievementAwardEntity> uaaList = this.selectList(new EntityWrapper<UserScoreAchievementAwardEntity>().eq("action", action));
		for(UserScoreAchievementAwardEntity uaa : uaaList){
			//按任务设定，检查是否完成
			if(uaa.getCat() == 1){ // 新手任务
				// 用userId获取积分奖励日志，为空则奖励积分并加日志
				List<UserLogEntity> elogs = userLogDao.selectList(new EntityWrapper<UserLogEntity>().eq("user_id", userId).eq("adjunct_info", action));
				if(elogs == null || elogs.size() == 0){
					rewardSocreAndLog(userId, action, logId, uaa.getBonus());
				}
			}else if(uaa.getCat() == 2){ // 日常任务
				// 用当前时间获取积分奖励日志，为空则奖励积分并加日志
				SimpleDateFormat sdFromat = new SimpleDateFormat("yyyyMMdd");
				List<UserLogEntity> elogs = userLogDao.queryUserLogByDay(userId, action, sdFromat.format(log.getLogTime()));
				if(elogs == null || elogs.size() == 0){
					rewardSocreAndLog(userId, action, logId, uaa.getBonus());
				}
			}else if(uaa.getCat() == 3){ // 消费设置
				int mm = log.getTradeMoney().intValue(); // 交易/消费金额
				int gz = Integer.parseInt(uaa.getCondi()); // 每多少钱给一份积分
				int sc = mm / gz * uaa.getBonus();
				if(sc > 0){
					rewardSocreAndLog(userId, action, logId, sc);
				}
			}
		}
		
	}
	
	
	void rewardSocreAndLog(long userId, String action, long logId, int bonus){
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		int oldScore = user.getScore();
		user.setScore(oldScore + bonus);
		// 用户获得积分
		userService.updateById(user);
		
		// 奖励积分日志
		UserLogAction logType = UserLogAction.U_AWARD_SCORE;
		Map<String,String> message = new HashMap<String,String>();
		Date now = new Date();
		String timestamp = now.getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType.toString());
		message.put("platform", null);
		message.put("tradeScore", bonus+"");
		message.put("score", oldScore+"");
		message.put("descr", logType.getDescr());
		message.put("adjunctInfo", action);
		message.put("token", MD5.md5(logType+"_"+logId+"_"+timestamp));
		
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
	}
	
}
