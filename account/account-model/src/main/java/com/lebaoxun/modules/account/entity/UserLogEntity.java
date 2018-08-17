package com.lebaoxun.modules.account.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-06-19 20:01:34
 */
@TableName("user_log")
public class UserLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String account;
	/**
	 * 日志时间
	 */
	private Date createTime;
	/**
	 * 日志时间
	 */
	private Date logTime;
	/**
	 * 日志类型
	 */
	private String logType;
	/**
	 * 来源
	 */
	private String platform;
	/**
	 * 交易金额
	 */
	private BigDecimal tradeMoney;
	/**
	 * 账户余额
	 */
	private BigDecimal money;
	/**
	 * 交易前积分
	 */
	private Integer score;
	/**
	 * 交易积分
	 */
	private Integer tradeScore;
	/**
	 * 日志说明
	 */
	private String descr;
	/**
	 * 日志参数
	 */
	private String adjunctInfo;
	/**
	 * 唯一码
	 */
	private String token;

	/**
	 * 设置：
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：日志时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：日志时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：日志类型
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}
	/**
	 * 获取：日志类型
	 */
	public String getLogType() {
		return logType;
	}
	/**
	 * 设置：交易金额
	 */
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	/**
	 * 获取：交易金额
	 */
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	/**
	 * 设置：账户余额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：账户余额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：日志说明
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	/**
	 * 获取：日志说明
	 */
	public String getDescr() {
		return descr;
	}
	/**
	 * 设置：日志参数
	 */
	public void setAdjunctInfo(String adjunctInfo) {
		this.adjunctInfo = adjunctInfo;
	}
	/**
	 * 获取：日志参数
	 */
	public String getAdjunctInfo() {
		return adjunctInfo;
	}
	/**
	 * 获取：用户名
	 */
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTradeScore() {
		return tradeScore;
	}
	public void setTradeScore(Integer tradeScore) {
		this.tradeScore = tradeScore;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
}
