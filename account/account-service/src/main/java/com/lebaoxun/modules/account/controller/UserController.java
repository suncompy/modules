package com.lebaoxun.modules.account.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.PwdUtil;
import com.lebaoxun.modules.account.em.UserLogAction;
import com.lebaoxun.modules.account.entity.UserEntity;
import com.lebaoxun.modules.account.service.UserService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import com.lebaoxun.soa.core.redis.lock.RedisLock;



/**
 * 用户表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-06-19 20:01:34
 */
@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public HttpServletRequest request;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
    @Autowired
    private UserService userService;

    @Value("${security.md5.password}")
	private String passwdSecret;
    /**
     * 列表
     */
    @RequestMapping("/account/user/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/user/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		UserEntity user = userService.selectById(id);
        return ResponseMessage.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/user/save")
    @RedisLock(value="account:user:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserEntity user){
		userService.save(adminId,user);
        return ResponseMessage.ok();
    }

    /**
     * 设置用户账户状态
     * @param adminId 管理员
     * @param userId 用户ID
     * @param scope 获取管理员host使用
     * @return
     */
    @RequestMapping("/account/user/disabled")
    @RedisLock(value="account:user:disabled:lock:#arg0:#arg1")
    ResponseMessage disabled(@RequestParam(value="adminId")Long adminId,
    		@RequestParam(value="userId")Long userId){
    	 userService.lock(userId, adminId);
    	 return ResponseMessage.ok();
    }
    
    /**
     * 修改密码
     * @param userId 用户ID
     * @param newPasswd 新密码（非加密）
     * @param adminId 操作人
     */
    @RequestMapping("/account/user/modifyPassword")
    @RedisLock(value="account:user:modifyPassword:lock:#arg0")
    ResponseMessage modifyPassword(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="newPasswd") String newPasswd,
    		@RequestParam(value="adminId",required=false) Long adminId){
    	userService.modifyPassword(userId, newPasswd, adminId);
    	return ResponseMessage.ok();
    }
    
    /**
     * 修改账户金额
     * @param userId 用户ID
     * @param amount 变更数量
     * @param adminId 操作人
     * @param logType 带字母U开头，为用户本人操作产生的日志
     * @param descr 操作说明
     */
    @RequestMapping("/account/user/modifyBalance")
    @RedisLock(value="account:user:modifyBalance:lock:#arg0")
    ResponseMessage modifyBalance(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="amount") BigDecimal amount,
    		@RequestParam(value="adminId",required=false) Long adminId,
    		@RequestParam(value="descr",required=false) String descr){
    	userService.modifyBalance(userId, amount, descr, adminId);
    	return ResponseMessage.ok();
    }
    
    /**
     * 余额支付
     * @param userId 用户ID
     * @param amount 变更数量
     * @param adminId 操作人
     * @param logType 带字母U开头，为用户本人操作产生的日志
     * @param descr 操作说明
     */
    @RequestMapping("/account/user/balance/pay")
    @RedisLock(value="account:user:balance:pay:lock:#arg0")
    ResponseMessage balancePay(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="tradeMoney") BigDecimal tradeMoney,
    		@RequestParam(value="platform",required=false) String platform,
    		@RequestParam(value="adjunctInfo") String adjunctInfo,
    		@RequestParam(value="descr",required=false) String descr){
    	
    	UserEntity user = userService.balancePay(userId, tradeMoney);
    	String logType = "BALANCE_PAY";
    	Date now = new Date();
    	Map<String,String> message = new HashMap<String,String>();
		String timestamp = now.getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType);
		message.put("platform", platform);
		message.put("tradeMoney", new BigDecimal("0").subtract(tradeMoney).toString());
		message.put("money", user.getBalance().toString());
		message.put("descr", descr);
		message.put("adjunctInfo", adjunctInfo);
		message.put("token", MD5.md5(logType+"_"+timestamp+"_"+adjunctInfo));
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
		
    	return ResponseMessage.ok();
    }
    
    @RequestMapping("/account/user/score/pay")
    @RedisLock(value="account:user:score:pay:lock:#arg0")
    ResponseMessage scorePay(@RequestParam(value="userId")Long userId, 
    		@RequestParam(value="score") Integer score,
    		@RequestParam(value="platform",required=false) String platform,
    		@RequestParam(value="adjunctInfo") String adjunctInfo,
    		@RequestParam(value="descr",required=false) String descr){
    	
    	UserEntity user = userService.scorePay(userId, score);
    	String logType = "SCORE_PAY";
    	Date now = new Date();
    	Map<String,String> message = new HashMap<String,String>();
		String timestamp = now.getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType);
		message.put("platform", platform);
		message.put("tradeMoney", null);
		message.put("score", user.getScore().toString());
		message.put("tradeScore", (0-score) + "");
		message.put("descr", descr);
		message.put("adjunctInfo", adjunctInfo);
		message.put("token", MD5.md5(logType+"_"+timestamp+"_"+adjunctInfo));
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
		
    	return ResponseMessage.ok();
    }
    /**
     * 修改头像
     * @param userId 用户ID
     * @param headimgurl 用户头像地址
     * @return
     */
    @RequestMapping("/account/user/modifyHeadimgurl")
    @RedisLock(value="account:user:modifyHeadimgurl:lock:#arg0")
    ResponseMessage modifyHeadimgurl(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="headimgurl") String headimgurl){
    	userService.modifyHeadimgurl(userId, headimgurl);
    	return ResponseMessage.ok();
    }
    
    /**
     * 修改用户信息
     * @param userId
     * @param user
     * @param logType
     * @param adminId
     * @param descr
     */
    @RequestMapping("/account/user/modifyInfo")
    @RedisLock(value="account:user:modifyInfo:lock:#arg0")
    ResponseMessage modifyInfo(@RequestParam(value="userId") Long userId,
    		@RequestBody UserEntity user, 
    		@RequestParam(value="adminId",required=false) Long adminId,
    		@RequestParam(value="descr",required=false) String descr){
    	userService.modifyInfo(userId, user, adminId, descr);
    	return ResponseMessage.ok();
    }
    
    /**
     * 绑定账号
     * @param userId
     * @param account
     */
    @RequestMapping("/account/user/bindMobile")
    @RedisLock(value="account:user:bindMobile:lock:#arg0")
    ResponseMessage bindMobile(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="mobile") String mobile, 
    		@RequestParam(value="password") String password){
    	
    	Date now = new Date();
    	
    	userService.bindMobile(userId, mobile, password);
    	
    	UserLogAction logType = UserLogAction.U_BIND_MOBILE;
		
		Map<String,String> message = new HashMap<String,String>();
		String timestamp = now.getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType.toString());
		message.put("platform", null);
		message.put("tradeMoney", null);
		message.put("money", null);
		message.put("descr", logType.getDescr());
		message.put("adjunctInfo", mobile);
		message.put("token", MD5.md5(logType.toString()+"_"+mobile+"_"+timestamp));
		
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
    	return ResponseMessage.ok();
    }
    
    /**
     * 绑定微信公众号openid
     * @param userId 用户ID
     * @param openid 微信openid
     */
    @RequestMapping("/account/user/bindOpenid")
    @RedisLock(value="account:user:bindOpenid:lock:#arg0")
    ResponseMessage bindOpenid(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="openid") String openid){
    	Date now = new Date();
    	userService.bindOpenid(userId, openid);
    	
    	UserLogAction logType = UserLogAction.U_BIND_OPENID;
		
		Map<String,String> message = new HashMap<String,String>();
		String timestamp = now.getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType.toString());
		message.put("platform", null);
		message.put("tradeMoney", null);
		message.put("money", null);
		message.put("descr", logType.getDescr());
		message.put("adjunctInfo", openid);
		message.put("token", MD5.md5(logType.toString()+"_"+openid+"_"+timestamp));
		
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
    	return ResponseMessage.ok();
    }
    
    /**
     * 微信公众号注册
     * @param userId
     * @param user
     * @param scope
     */
    @RequestMapping("/account/user/wechatOARegister")
    @RedisLock(value="account:user:wechatOARegister:lock:#arg0")
    ResponseMessage wechatOARegister(@RequestParam(value="userId") Long userId, 
    		@RequestBody UserEntity user){
    	UserEntity entity = userService.wechatOARegister(userId, user);
    	
    	UserLogAction logType = UserLogAction.U_REGISTER;
		
		Map<String,String> message = new HashMap<String,String>();
		String timestamp = entity.getCreateTime().getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType.toString());
		message.put("platform", null);
		message.put("tradeMoney", new BigDecimal(0.00).toString());
		message.put("money", entity.getBalance().toString());
		message.put("descr", logType.getDescr());
		message.put("adjunctInfo", "WECHAT_OA_REGISTER");
		message.put("token", MD5.md5(logType.toString()+"_WECHAT_OA_REGISTER_"+timestamp));
		
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
    	return ResponseMessage.ok();
    }
    
    /**
     * 微信小程序注册
     * @param userId
     * @param user
     * @param scope
     */
    @RequestMapping("/account/user/wechatAppRegister")
    @RedisLock(value="account:user:wechatAppRegister:lock:#arg0")
    ResponseMessage wechatAppRegister(@RequestParam(value="userId") Long userId, 
    		@RequestBody UserEntity user){
    	UserEntity entity = userService.wechatAppRegister(userId, user);
    	
    	UserLogAction logType = UserLogAction.U_REGISTER;
		
		Map<String,String> message = new HashMap<String,String>();
		String timestamp = entity.getCreateTime().getTime()+"";
		message.put("userId", userId+"");
		message.put("timestamp", timestamp);
		message.put("logType", logType.toString());
		message.put("platform", null);
		message.put("tradeMoney", new BigDecimal(0.00).toString());
		message.put("money", entity.getBalance().toString());
		message.put("descr", logType.getDescr());
		message.put("adjunctInfo", "WECHAT_APP_REGISTER");
		message.put("token", MD5.md5(logType.toString()+"_WECHAT_OA_REGISTER_"+timestamp));
		
		logger.info("rabbit|sendContractDirect|message={}",message);
		rabbitmqSender.sendContractDirect("account.log.queue",
				new Gson().toJson(message));
		
		if(entity.getInviter() != null){
			Map<String,String> imessage = new HashMap<String,String>();
			imessage.put("userId", entity.getInviter()+"");
			imessage.put("timestamp", timestamp);
			imessage.put("logType", "U_INVITE_REGISTER");
			imessage.put("platform", null);
			imessage.put("descr", "邀请用户注册");
			imessage.put("adjunctInfo", userId+"");
			imessage.put("token", MD5.md5("U_INVITE_REGISTER"+"_"+userId));
			
			logger.info("rabbit|sendContractDirect|message={}",message);
			rabbitmqSender.sendContractDirect("account.log.queue",
					new Gson().toJson(imessage));
		}
    	return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/account/user/delete")
    @RedisLock(value="account:user:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId") Long adminId,@RequestBody String[] ids){
		userService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/account/user/findByUserId")
    UserEntity findByUserId(@RequestParam("userId") Long userId){
		return userService.selectOne( new EntityWrapper<UserEntity>().eq("user_id", userId));
	}
	
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
	@RequestMapping("/account/user/findByAccount")
	UserEntity findByAccount(@RequestParam("account") String account){
		return userService.selectOne( new EntityWrapper<UserEntity>().eq("account", account));
	}
	
	/**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
	@RequestMapping("/account/user/findByOpenid")
	UserEntity findByOpenid(@RequestParam("openid") String openid,
			@RequestParam(value="groupid",required=false) String groupid){
		return userService.selectOne( new EntityWrapper<UserEntity>().eq("openid", openid));
	}
	
	/**
	 * 根据微信小程序openid获取
	 * @param username
	 * @return
	 */
	@RequestMapping("/account/user/findByWxAppOpenid")
	UserEntity findByWxAppOpenid(@RequestParam("openid") String openid,
			@RequestParam(value="groupid",required=false) String groupid){
		return userService.selectOne( new EntityWrapper<UserEntity>().eq("wx_app_openid", openid));
	}
	
	/**
     * 根据用户名，密码验证登录
     * @param username
     * @return
     */
	@RequestMapping("/account/user/login")
	UserEntity login(@RequestParam("username") String username,@RequestParam("password") String password){
		return userService.selectOne(new EntityWrapper<UserEntity>().eq("account", username).eq("password", PwdUtil.getMd5Password(passwdSecret, username, password)));
	}
	
	/**
     * 修改最后登录时间
     * @param userId
     */
	@RequestMapping("/account/user/modifyLastLogin")
	@RedisLock(value="account:user:modifyLastLogin:lock:#arg0")
    ResponseMessage modifyLastLogin(@RequestParam("userId") Long userId,
    		@RequestParam("lastLoginTime") Long lastLoginTime){
		userService.modifyLastLogin(userId, lastLoginTime);
        return ResponseMessage.ok();
    }

}
