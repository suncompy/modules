package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 每周菜谱
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@TableName("food_week_menu")
public class FoodWeekMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 星期数
	 */
	private Integer week;
	/**
	 * 参考价格
	 */
	private Float totalProduct;
	/**
	 * 餐品ID
	 */
	private Integer productId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;

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
	 * 设置：星期数
	 */
	public void setWeek(Integer week) {
		this.week = week;
	}
	/**
	 * 获取：星期数
	 */
	public Integer getWeek() {
		return week;
	}
	/**
	 * 设置：参考价格
	 */
	public void setTotalProduct(Float totalProduct) {
		this.totalProduct = totalProduct;
	}
	/**
	 * 获取：参考价格
	 */
	public Float getTotalProduct() {
		return totalProduct;
	}
	/**
	 * 设置：餐品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：餐品ID
	 */
	public Integer getProductId() {
		return productId;
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
