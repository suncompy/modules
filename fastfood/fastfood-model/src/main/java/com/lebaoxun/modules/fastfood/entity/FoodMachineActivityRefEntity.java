package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:23
 */
@TableName("food_machine_activity_ref")
public class FoodMachineActivityRefEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id=0;
	/**
	 * 机器id
	 */
	private Integer macId;
	/**
	 * 活动类型  1.首单 2.折扣 3.返现
	 */
	private Integer activityType;
	/**
	 * 
	 */
	private Integer createBy;
	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 1.关联 2.否
	 */
	@TableField(exist = false)
	private Integer isRef;

	@TableField(exist = false)
	private String activityName;

	@TableField(exist = false)
	private Integer rowId;

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

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
	 * 设置：机器id
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：机器id
	 */
	public Integer getMacId() {
		return macId;
	}
	/**
	 * 设置：活动类型  1.首单 2.折扣 3.返现
	 */
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	/**
	 * 获取：活动类型  1.首单 2.折扣 3.返现
	 */
	public Integer getActivityType() {
		return activityType;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getIsRef() {
		return isRef;
	}

	public void setIsRef(Integer isRef) {
		this.isRef = isRef;
	}
}
