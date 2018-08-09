package com.lebaoxun.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 连续折扣
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
@TableName("operate_activity_keep_discount")
public class OperateActivityKeepDiscountEntity implements Serializable {
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
	 * 启用时间
	 */
	private Date startTime;
	/**
	 * 有效期，单位天
	 */
	private Integer period;
	/**
	 * 递增
	 */
	private Float proIncr;
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
	 * 设置：启用时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：启用时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：有效期，单位天
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	/**
	 * 获取：有效期，单位天
	 */
	public Integer getPeriod() {
		return period;
	}
	/**
	 * 设置：递增
	 */
	public void setProIncr(Float proIncr) {
		this.proIncr = proIncr;
	}
	/**
	 * 获取：递增
	 */
	public Float getProIncr() {
		return proIncr;
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
