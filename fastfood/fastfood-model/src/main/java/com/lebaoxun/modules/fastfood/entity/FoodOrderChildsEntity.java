package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_order_childs")
public class FoodOrderChildsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单商品ID
	 */
	@TableId
	private Long orderProductId = 0l;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 餐品分类ID
	 */
	private Integer productCatId;
	/**
	 * 餐品ID
	 */
	private Integer productId;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 积分
	 */
	private Integer score;
	/**
	 * 商品总数量
	 */
	private Integer buyNumber;
	/**
	 * 商品总积分
	 */
	private Integer productScore;
	/**
	 * 商品总金额
	 */
	private BigDecimal productAmount;
	/**
	 * 订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价
	 */
	private Integer status;

	/**
	 * 设置：订单商品ID
	 */
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	/**
	 * 获取：订单商品ID
	 */
	public Long getOrderProductId() {
		return orderProductId;
	}
	/**
	 * 设置：订单ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单ID
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：餐品分类ID
	 */
	public void setProductCatId(Integer productCatId) {
		this.productCatId = productCatId;
	}
	/**
	 * 获取：餐品分类ID
	 */
	public Integer getProductCatId() {
		return productCatId;
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
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：积分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * 获取：积分
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * 设置：商品总数量
	 */
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	/**
	 * 获取：商品总数量
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}
	/**
	 * 设置：商品总积分
	 */
	public void setProductScore(Integer productScore) {
		this.productScore = productScore;
	}
	/**
	 * 获取：商品总积分
	 */
	public Integer getProductScore() {
		return productScore;
	}
	/**
	 * 设置：商品总金额
	 */
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * 获取：商品总金额
	 */
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	/**
	 * 设置：订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价
	 */
	public Integer getStatus() {
		return status;
	}
}
