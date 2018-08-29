package com.lebaoxun.modules.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.PwdUtil;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.commons.utils.ValidatorUtils;
import com.lebaoxun.modules.account.dao.UserDao;
import com.lebaoxun.modules.account.dao.UserLogDao;
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
		String status = "N";
		if("N".equals(user.getStatus())){
			status = "Y";
		}
		
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
		String passwd = PwdUtil.getMd5Password(passwdSecret,user.getAccount(), newPasswd);
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setPassword(passwd);
		entity.setLastUpdateTime(new Date());
		this.updateById(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserEntity modifyBalance(Long userId,BigDecimal amount, String descr, Long adminId) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setBalance(user.getBalance().add(amount));
		this.updateById(entity);
		return user;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserEntity balancePay(Long userId, BigDecimal tradeMoney) {
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		if(user.getBalance().compareTo(tradeMoney) < 0){
			throw new I18nMessageException("40008");
		}
		user.setBalance(user.getBalance().subtract(tradeMoney));
		this.updateById(user);
		return user;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserEntity scorePay(Long userId, Integer score) {
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		if(user.getScore() < score){
			throw new I18nMessageException("40007");
		}
		user.setScore(user.getScore() - score);
		this.updateById(user);
		return user;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public UserEntity recharge(Long userId, String orderNo, Long buyTime,
			String total_fee) {
		// TODO Auto-generated method stub
		BigDecimal fee = new BigDecimal(total_fee);
		
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		int count = userLogDao.selectCount(new EntityWrapper<UserLogEntity>().eq("user_id", userId).eq("adjunct_info", orderNo));
		if(count > 0){//已完成充值
			return null;
		}
		Date time = new Date(buyTime),
				now = new Date();
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setBalance(user.getBalance().add(fee));
		entity.setLastBuyTime(time);
		entity.setPayTotal(user.getPayTotal()+1);
		boolean isMonth = DateFormatUtils.format(time, "yyyyMM").equals(DateFormatUtils.format(now, "yyyyMM"));
		Integer payCurrentTotal = 0;
		if(isMonth){
			if(entity.getPayCurrentTotal() == null){
				entity.setPayCurrentTotal(0);
			}
			payCurrentTotal = entity.getPayCurrentTotal() +1;
		}
		entity.setPayCurrentTotal(payCurrentTotal);
		this.updateById(entity);
		return user;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyHeadimgurl(Long userId, String headimgurl) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setHeadimgurl(headimgurl);
		entity.setLastUpdateTime(new Date());
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
		entity.setLastUpdateTime(new Date());
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
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean insertLog(UserLogEntity log) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", log.getUserId()));
		if(user == null){
			return false;
		}
		int count = userLogDao.selectCount(new EntityWrapper<UserLogEntity>().eq("user_id", log.getUserId()).eq("token", log.getToken()));
		if(count > 0){
			return false;
		}
		log.setAccount(user.getAccount());
		userLogDao.insert(log);
		return true;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void modifyLastLogin(Long userId,Long lastLoginTime) {
		// TODO Auto-generated method stub
		UserEntity user = this.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
		if(user == null){
			throw new I18nMessageException("500");
		}
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setLastLoginTime(new Date(lastLoginTime));
		this.updateById(entity);
	}
}
