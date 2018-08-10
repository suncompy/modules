package com.lebaoxun.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值返现表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:30
 */
@TableName("operate_activity_pay_cash_back")
public class OperateActivityPayCashBackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 活动名称
	 */
	private String name;
	/**
	 * 金额
	 */
	private Float amount;
	/**
	 * 人次
	 */
	private Integer personTime;
	/**
	 * 递减金额
	 */
	private Integer wane;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 状态0=禁用，1=启用
	 */
	private Integer use;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 编辑人
	 */
	private Long updateBy;
	/**
	 * 修改时间
	 */
	private Date updateTime;

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
	 * 设置：活动名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：活动名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：金额
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	/**
	 * 获取：金额
	 */
	public Float getAmount() {
		return amount;
	}
	/**
	 * 设置：人次
	 */
	public void setPersonTime(Integer personTime) {
		this.personTime = personTime;
	}
	/**
	 * 获取：人次
	 */
	public Integer getPersonTime() {
		return personTime;
	}
	/**
	 * 设置：递减金额
	 */
	public void setWane(Integer wane) {
		this.wane = wane;
	}
	/**
	 * 获取：递减金额
	 */
	public Integer getWane() {
		return wane;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：状态0=禁用，1=启用
	 */
	public void setUse(Integer use) {
		this.use = use;
	}
	/**
	 * 获取：状态0=禁用，1=启用
	 */
	public Integer getUse() {
		return use;
	}
	/**
	 * 设置：描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：编辑人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：编辑人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
