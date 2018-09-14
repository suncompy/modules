package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 订单表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_order")
public class FoodOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId
	private Long id = 0l;
	/**
	 * 订单编号,系统生成
	 */
	private String orderNo;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 支付方式 0=积分兑换，1=在线支付，2=余额支付
	 */
	private Integer payType;
	/**
	 * 订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价
	 */
	private Integer orderStatus;
	/**
	 * 订单金额
	 */
	private BigDecimal orderAmount;
	/**
	 * 红包抵扣金额
	 */
	private BigDecimal redPackedAmount;
	/**
	 * 订单积分
	 */
	private Integer orderScore;
	/**
	 * 支付金额，折扣抵消
	 */
	private BigDecimal payAmount;
	/**
	 * 商品总数量
	 */
	private Integer buyNumber;
	/**
	 * 取餐机ID
	 */
	private Integer macId;
	
	@TableField(exist=false)
	private String macAreaCode;
	
	@TableField(exist=false)
	private String macAddress;
	/**
	 * 活动ID
	 */
	private Integer activityId;
	/**
	 * 活动类型
	 */
	private String activityType;
	/**
	 * 活动优惠
	 */
	private BigDecimal activityFee;
	/**
	 * 优惠券抵扣
	 */
	private Integer couponId;
	/**
	 * 0=现金，1=折扣
	 */
	private Integer couponType;
	/**
	 * 折扣额度或现金数
	 */
	private BigDecimal couponFee;
	/**
	 * 优惠券使用条件
	 */
	private BigDecimal couponUseRestrict;
	/**
	 * 红包抵扣
	 */
	private Integer redPackedId;
	/**
	 * 订单来源
	 */
	private String source;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 取餐时间
	 */
	private Date takeFoodTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 取餐二维码
	 */
	private String qrCode;
	
	@TableField(exist=false)
	private List<FoodOrderChildsEntity> childs;
	
	/**
	 * 取餐码
	 */
	@TableField(exist=false)
	private Integer takeFoodCode;

	/**
	 * 设置：订单ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：订单ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：订单编号,系统生成
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号,系统生成
	 */
	public String getOrderNo() {
		return orderNo;
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
	 * 设置：支付方式 0=积分兑换，1=在线支付，2=余额支付
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付方式 0=积分兑换，1=在线支付，2=余额支付
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：订单状态 -2=已删除，-1=已取消，0=待支付，1=已支付，2=已取单，3=已评价
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：订单金额
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * 获取：订单金额
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	/**
	 * 设置：订单金额
	 */
	public void setRedPackedAmount(BigDecimal redPackedAmount) {
		this.redPackedAmount = redPackedAmount;
	}
	/**
	 * 获取：订单金额
	 */
	public BigDecimal getRedPackedAmount() {
		return redPackedAmount;
	}
	/**
	 * 设置：订单积分
	 */
	public void setOrderScore(Integer orderScore) {
		this.orderScore = orderScore;
	}
	/**
	 * 获取：订单积分
	 */
	public Integer getOrderScore() {
		return orderScore;
	}
	/**
	 * 设置：支付金额，折扣抵消
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：支付金额，折扣抵消
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
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
	 * 设置：取餐机ID
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：取餐机ID
	 */
	public Integer getMacId() {
		return macId;
	}
	/**
	 * 设置：活动ID
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动ID
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：活动类型
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * 获取：活动类型
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * 设置：优惠券抵扣
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：优惠券抵扣
	 */
	public Integer getCouponId() {
		return couponId;
	}
	/**
	 * 设置：红包抵扣
	 */
	public void setRedPackedId(Integer redPackedId) {
		this.redPackedId = redPackedId;
	}
	/**
	 * 获取：红包抵扣
	 */
	public Integer getRedPackedId() {
		return redPackedId;
	}
	/**
	 * 设置：订单来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：订单来源
	 */
	public String getSource() {
		return source;
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
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public BigDecimal getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(BigDecimal couponFee) {
		this.couponFee = couponFee;
	}
	public BigDecimal getCouponUseRestrict() {
		return couponUseRestrict;
	}
	public void setCouponUseRestrict(BigDecimal couponUseRestrict) {
		this.couponUseRestrict = couponUseRestrict;
	}
	public BigDecimal getActivityFee() {
		return activityFee;
	}
	public void setActivityFee(BigDecimal activityFee) {
		this.activityFee = activityFee;
	}
	public List<FoodOrderChildsEntity> getChilds() {
		return childs;
	}
	public void setChilds(List<FoodOrderChildsEntity> childs) {
		this.childs = childs;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public Integer getTakeFoodCode() {
		return takeFoodCode;
	}
	public void setTakeFoodCode(Integer takeFoodCode) {
		this.takeFoodCode = takeFoodCode;
	}
	public Date getTakeFoodTime() {
		return takeFoodTime;
	}
	public void setTakeFoodTime(Date takeFoodTime) {
		this.takeFoodTime = takeFoodTime;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getMacAreaCode() {
		return macAreaCode;
	}
	public void setMacAreaCode(String macAreaCode) {
		this.macAreaCode = macAreaCode;
	}
}
