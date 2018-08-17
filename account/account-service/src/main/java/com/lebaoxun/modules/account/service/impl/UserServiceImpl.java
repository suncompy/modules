package com.lebaoxun.modules.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import com.lebaoxun.commons.utils.PwdUtil;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.commons.utils.ValidatorUtils;
import com.lebaoxun.modules.account.dao.UserDao;
import com.lebaoxun.modules.account.dao.UserLogDao;
import com.lebaoxun.modules.account.em.UserLogAction;
import com.lebaoxun.modules.account.entity.UserEntity;
import com.lebaoxun.modules.account.entity.UserLogEntity;
import com.lebaoxun.modules.account.service.UserService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Resource
	private UserLogDao userLogDao;
	
	@Value("${security.md5.password}")
	private String passwdSecret;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String account = (String) params.get("account");
        Page<UserEntity> page = this.selectPage(
                new Query<UserEntity>(params).getPage(),
                new EntityWrapper<UserEntity>().like(StringUtils.isNotBlank(account), "account", account)
        );

        return new PageUtils(page);
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(Long adminId,UserEntity user) {
    	// TODO Auto-generated method stub
    	
    	UserEntity u = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", user.getUserId()));
		if(u != null){
			throw new I18nMessageException("-1","用户ID已存在");
		}
		
		if(StringUtils.isBlank(user.getMobile()) || !ValidatorUtils.checkTel(user.getMobile())){
			throw new I18nMessageException("-1","手机号格式不正确！");
		}
		
		u = this.selectOne( new EntityWrapper<UserEntity>().eq("account", user.getAccount()).or().eq("mobile", user.getMobile()));
		if(u != null){
			if(u.getAccount().equals(user.getAccount())){
				throw new I18nMessageException("-1","账号已存在！");
			}
			
			if(u.getMobile().equals(user.getMobile())){
				throw new I18nMessageException("-1","手机号已存在！");
			}
		}
		logger.debug("passwdSecret={},account={},password={}",passwdSecret,user.getAccount(), user.getPassword());
		String passwd = PwdUtil.getMd5Password(passwdSecret,user.getAccount(), user.getPassword());
		
    	user.setCreateTime(new Date());
    	user.setBalance(new BigDecimal(0.00));
    	user.setLevel(0);
    	user.setPassword(passwd);
    	this.baseMapper.insert(user);
    }
    
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void lock(Long userId, Long adminId) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		String status = "N",adjunctInfo = adminId+"";
		UserLogAction logType = UserLogAction.A_LOCK;
		if("N".equals(user.getStatus())){
			logType = UserLogAction.A_UNLOCK;
			status = "Y";
		}
		
		insertLog(user, logType, new BigDecimal(0.00), logType.getDescr(), adjunctInfo);
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setStatus(status);
		this.updateById(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyPassword(Long userId, String newPasswd,
			Long adminId) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		String adjunctInfo = null;
		UserLogAction logType = UserLogAction.U_MODIFY_PASSWD;
		if(adminId != null){
			adjunctInfo = adminId+"";
			logType = UserLogAction.A_MODIFY_PASSWD;
		}
		insertLog(user, logType, new BigDecimal(0.00), logType.getDescr(), adjunctInfo);
		
		String passwd = PwdUtil.getMd5Password(passwdSecret,user.getAccount(), newPasswd);
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setPassword(passwd);
		this.updateById(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyBalance(Long userId,BigDecimal amount, String descr, Long adminId) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		UserLogAction logType;
		String adjunctInfo = null;
		if(amount.compareTo(new BigDecimal(0)) > 0){
			logType = UserLogAction.U_BALANCE_ADD;
			if(adminId != null){
				adjunctInfo = adminId+"";
				logType = UserLogAction.A_BALANCE_ADD;
			}
		}else{
			logType = UserLogAction.U_BALANCE_REDUCE;
			if(adminId != null){
				adjunctInfo = adminId+"";
				logType = UserLogAction.A_BALANCE_REDUCE;
			}
		}
		if(StringUtils.isBlank(descr)){
			descr = logType.getDescr();
		}
		insertLog(user, logType, amount, descr, adjunctInfo);
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setBalance(user.getBalance().add(amount));
		this.updateById(entity);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void recharge(Long userId, String orderNo, Long buyTime,
			String total_fee) {
		// TODO Auto-generated method stub
		BigDecimal fee = new BigDecimal(total_fee);
		
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		UserLogAction logType = UserLogAction.U_PAY_BALANCE_ADD;
		String descr = logType.getDescr();
		
		int count = userLogDao.selectCount(new EntityWrapper<UserLogEntity>().eq("user_id", userId).eq("adjunct_info", orderNo));
		if(count > 0){//已完成充值
			return;
		}
		UserLogEntity log = new UserLogEntity();
		log.setAdjunctInfo(orderNo);
		descr = logType.getDescr();
		log.setAccount(user.getAccount());
		log.setCreateTime(new Date());
		log.setDescr(descr);
		log.setLogType(logType.toString());
		log.setMoney(user.getBalance());
		log.setTradeMoney(fee);
		log.setUserId(user.getUserId());
		
		userLogDao.insert(log);
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setBalance(user.getBalance().add(fee));
		this.updateById(entity);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyHeadimgurl(Long userId, String headimgurl) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		UserLogAction logType = UserLogAction.U_MODIFY_INFO;
		insertLog(user, logType, new BigDecimal(0.00), logType.getDescr(), headimgurl);
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setHeadimgurl(headimgurl);
		this.updateById(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyInfo(Long userId, UserEntity q,
			Long adminId, String descr) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		UserLogAction logType = UserLogAction.U_MODIFY_INFO;
		String adjunctInfo = null;
		if(adminId != null){
			adjunctInfo = adminId+"";
			logType = UserLogAction.A_MODIFY_INFO;
		}
		insertLog(user, logType, null, logType.getDescr(), adjunctInfo);
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		if(StringUtils.isNotBlank(q.getCity())){
			entity.setCity(q.getCity());
		}
		if(StringUtils.isNotBlank(q.getCountry())){
			entity.setCountry(q.getCountry());
		}
		if(StringUtils.isNotBlank(q.getGroupid())){
			entity.setGroupid(q.getGroupid());
		}
		entity.setHeadimgurl(user.getHeadimgurl());
		if(StringUtils.isNotBlank(q.getLanguage())){
			entity.setLanguage(q.getLanguage());
		}
		if(StringUtils.isNotBlank(q.getNickname())){
			entity.setNickname(q.getNickname());
		}
		if(StringUtils.isNotBlank(q.getProvince())){
			entity.setProvince(q.getProvince());
		}
		if(StringUtils.isNotBlank(q.getRemark())){
			entity.setRemark(q.getRemark());
		}
		if(q.getSex() != null){
			entity.setSex(q.getSex());
		}
		if(StringUtils.isNotBlank(q.getMobile())){
			entity.setMobile(q.getMobile());
		}
		if(StringUtils.isNotBlank(q.getRealname())){
			entity.setRealname(q.getRealname());
		}
		if(StringUtils.isNotBlank(q.getIdentity())){
			entity.setIdentity(q.getIdentity());
		}
		if(StringUtils.isNotBlank(q.getBirthday())){
			entity.setBirthday(q.getBirthday());
		}
		this.updateById(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void bindMobile(Long userId,String mobile, String password) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(mobile) || !ValidatorUtils.checkTel(mobile)){
			throw new I18nMessageException("-1","手机号格式不正确！");
		}
		
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		
		String passwd = PwdUtil.getMd5Password(passwdSecret,mobile, password);
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setAccount(mobile);
		entity.setMobile(mobile);
		entity.setPassword(passwd);
		this.updateById(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void bindOpenid(Long userId, String openid) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setOpenid(openid);
		this.updateById(entity);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserEntity wechatOARegister(Long userId, UserEntity q) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user != null){
			throw new I18nMessageException("500");
		}
		UserEntity entity = new UserEntity();
		entity.setCity(q.getCity());
		entity.setCountry(q.getCountry());
		entity.setGroupid(q.getGroupid());
		entity.setHeadimgurl(q.getHeadimgurl());
		entity.setLanguage(q.getLanguage());
		entity.setNickname(q.getNickname());
		entity.setProvince(q.getProvince());
		entity.setRemark(q.getRemark());
		entity.setSex(q.getSex());
		entity.setBalance(new BigDecimal(0.00));
		entity.setScore(0);
		entity.setCreateTime(new Date());
		entity.setOpenid(q.getOpenid());
		entity.setUnionid(q.getUnionid());
		entity.setType(q.getType());
		entity.setSource("SELF_WECHAT_OA");
		entity.setUserId(userId);
		entity.setStatus("Y");
		
		this.insert(entity);
		
		return entity;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void loginLog(Long userId, UserLogAction logType, String adjunctInfo,
			String descr) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		insertLog(user, logType, new BigDecimal(0.00), descr, adjunctInfo);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserEntity wechatAppRegister(Long userId, UserEntity q) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user != null){
			throw new I18nMessageException("50001");
		}
		user = this.selectOne( new EntityWrapper<UserEntity>().eq("account", q.getAccount()));
		if(user != null){
			throw new I18nMessageException("50001");
		}
		logger.debug("passwdSecret={},account={},password={}",passwdSecret,q.getAccount(), q.getPassword());
		String password = PwdUtil.getMd5Password(passwdSecret,q.getAccount(), q.getPassword());
		Date now = new Date();
		UserEntity entity = new UserEntity();
		/*entity.setCity(q.getCity());
		entity.setCountry(q.getCountry());
		entity.setHeadimgurl(q.getHeadimgurl());
		entity.setLanguage(q.getLanguage());
		entity.setNickname(q.getNickname());
		entity.setProvince(q.getProvince());*/
		entity.setAccount(q.getAccount());
		entity.setMobile(q.getMobile());
		entity.setPassword(password);
		entity.setInviter(q.getInviter());
		entity.setBalance(new BigDecimal(0.00));
		entity.setScore(0);
		entity.setLevel(0);
		entity.setCreateTime(now);
		entity.setWxAppOpenid(q.getWxAppOpenid());
		entity.setType(q.getType());
		entity.setSource("SELF_WECHAT_APP");
		entity.setUserId(userId);
		entity.setStatus("Y");
		this.insert(entity);
		return entity;
	}
	
	void insertLog(UserEntity user,UserLogAction logType,BigDecimal tradeMoney,String descr,String adjunctInfo){
		UserLogEntity log = new UserLogEntity();
		if(adjunctInfo != null){
			log.setAdjunctInfo(adjunctInfo);
		}
		if(StringUtils.isBlank(descr)){
			descr = logType.getDescr();
		}
		log.setAccount(user.getAccount());
		log.setCreateTime(new Date());
		log.setDescr(descr);
		log.setLogType(logType.toString());
		log.setMoney(user.getBalance());
		log.setTradeMoney(tradeMoney);
		log.setUserId(user.getUserId());
		
		userLogDao.insert(log);
	}
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertLog(UserLogEntity log) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", log.getUserId()));
		if(user == null){
			return;
		}
		int count = userLogDao.selectCount(new EntityWrapper<UserLogEntity>().eq("user_id", log.getUserId()).eq("token", log.getToken()));
		if(count > 0){
			return;
		}
		log.setAccount(user.getAccount());
		userLogDao.insert(log);
	}
}
