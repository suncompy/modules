package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_shopping_cart")
public class FoodShoppingCartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 购物车ID
	 */
	@TableId
	private Long cartId = 0l;
	/**
	 * 餐品ID
	 */
	private Integer productId;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 购买数量
	 */
	private Integer buyNumber;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 购物车状态：0,未选中；1,选中
	 */
	private Integer checkStatus;

	/**
	 * 设置：购物车ID
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	/**
	 * 获取：购物车ID
	 */
	public Long getCartId() {
		return cartId;
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
	 * 设置：购买数量
	 */
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	/**
	 * 获取：购买数量
	 */
	public Integer getBuyNumber() {
		return buyNumber;
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
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：购物车状态：0,未选中；1,选中
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：购物车状态：0,未选中；1,选中
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}
}
