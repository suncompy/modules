package com.lebaoxun.modules.manager.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员日志表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-26 12:04:12
 */
@TableName("sys_user_log")
public class SysUserLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	/**
	 * 用户ID
	 */
	private Long adminId;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 日志时间
	 */
	private Date createTime;
	/**
	 * 日志类型
	 */
	private String logType;
	/**
	 * 日志说明
	 */
	private String descr;
	/**
	 * 日志参数
	 */
	private String adjunctInfo;
	/**
	 * 唯一标识
	 */
	private String token;
	/**
	 * 日志时间
	 */
	private Date logTime;
	
	private String refId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户ID
	 */
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getAdminId() {
		return adminId;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
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
	 * 设置：唯一标识
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：唯一标识
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：日志时间
	 */
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	/**
	 * 获取：日志时间
	 */
	public Date getLogTime() {
		return logTime;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
}
