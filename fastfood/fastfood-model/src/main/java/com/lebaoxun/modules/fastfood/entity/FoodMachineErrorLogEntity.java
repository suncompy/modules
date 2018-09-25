package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 终端机器错误日志表
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-23 19:35:01
 */
@TableName("food_machine_error_log")
public class FoodMachineErrorLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 日志等级
	 */
	private Integer logLever;
	/**
	 * 日志类型
	 */
	private Integer logType;
	/**
	 * 日志内容
	 */
	private String logContex;
	/**
	 * 日志参数
	 */
	private String logParams;
	/**
	 * 日志时间
	 */
	private String createTime;

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
	 * 设置：机器ID
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：机器ID
	 */
	public Integer getMacId() {
		return macId;
	}
	/**
	 * 设置：日志等级
	 */
	public void setLogLever(Integer logLever) {
		this.logLever = logLever;
	}
	/**
	 * 获取：日志等级
	 */
	public Integer getLogLever() {
		return logLever;
	}
	/**
	 * 设置：日志类型
	 */
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	/**
	 * 获取：日志类型
	 */
	public Integer getLogType() {
		return logType;
	}
	/**
	 * 设置：日志内容
	 */
	public void setLogContex(String logContex) {
		this.logContex = logContex;
	}
	/**
	 * 获取：日志内容
	 */
	public String getLogContex() {
		return logContex;
	}
	/**
	 * 设置：日志参数
	 */
	public void setLogParams(String logParams) {
		this.logParams = logParams;
	}
	/**
	 * 获取：日志参数
	 */
	public String getLogParams() {
		return logParams;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
