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
 * @date 2018-08-17 15:32:24
 */
@TableName("food_machine_pro_activity_ref")
public class FoodMachineProActivityRefEntity implements Serializable {
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
	 * 货道id
	 */
	private Integer aisleId;
	/**
	 * 产品id
	 */
	@TableField(exist = false)
	private Integer productId;

	/**
	 * 产品id
	 */
	@TableField(exist = false)
	private String productName;
	/**
	 * 活动类型  1.首单 2.折扣 3.返现
	 */
	private Integer activityType;
	/**
	 * 1.关联 2.否
	 */
	@TableField(exist = false)
	private Integer isRef;

	@TableField(exist = false)
	private String activityName;
	/**
	 * 
	 */
	private Integer createBy;
	/**
	 * 
	 */
	private Date createTime;

	@TableField(exist = false)
	private Integer rowId;

	public Integer getAisleId() {
		return aisleId;
	}

	public void setAisleId(Integer aisleId) {
		this.aisleId = aisleId;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getIsRef() {
		return isRef;
	}

	public void setIsRef(Integer isRef) {
		this.isRef = isRef;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
}
