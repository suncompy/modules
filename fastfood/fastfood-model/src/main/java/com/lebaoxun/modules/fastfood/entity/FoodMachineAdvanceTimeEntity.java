package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 取餐机预定时间配置
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_machine_advance_time")
public class FoodMachineAdvanceTimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 货道ID
	 */
	private Integer aisleId;
	/**
	 * 预定时间yyyy-MM-dd
	 */
	private String time;
	/**
	 * 预定折扣比例
	 */
	private Float discount;

	/**
	 * 产品ID
	 */
	private Integer productId;

	private Integer productCatId;

	/**
	 * 产品名称
	 */
	@TableField(exist = false)
	private String productName;

	/**
	 * 日期是否预定 1.是 2.否
	 */
	@TableField(exist = false)
	private Integer isPre;

	@TableField(exist = false)
	private Integer rowId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;

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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getIsPre() {
		return isPre;
	}

	public void setIsPre(Integer isPre) {
		this.isPre = isPre;
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
	 * 设置：预定时间yyyy-MM-dd
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 获取：预定时间yyyy-MM-dd
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 设置：预定折扣比例
	 */
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	/**
	 * 获取：预定折扣比例
	 */
	public Float getDiscount() {
		return discount;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
}
