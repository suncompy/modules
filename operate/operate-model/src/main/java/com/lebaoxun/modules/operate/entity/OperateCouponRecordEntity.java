package com.lebaoxun.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券领取记录
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
@TableName("operate_coupon_record")
public class OperateCouponRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 优惠券ID
	 */
	private Integer couponId;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 产品ID
	 */
	private Integer proudctId;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 0=现金，1=折扣
	 */
	private Integer type;
	/**
	 * 折扣额度或现金数
	 */
	private BigDecimal amount;
	/**
	 * 使用限制，满x元可用
	 */
	private Integer useRestrict;
	/**
	 * 启用时间
	 */
	private Date startTime;
	/**
	 * 有效期，单位天
	 */
	private Integer period;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 领取人
	 */
	private Long userId;
	/**
	 * 状态0=未使用，1=已使用
	 */
	private Integer use;
	/**
	 * 使用订单号
	 */
	private String orderNo;
	/**
	 * 使用时间
	 */
	private Date useTime;

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
	 * 设置：优惠券ID
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：优惠券ID
	 */
	public Integer getCouponId() {
		return couponId;
	}
	/**
	 * 设置：优惠券名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：优惠券名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：0=现金，1=折扣
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：0=现金，1=折扣
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：折扣额度或现金数
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：折扣额度或现金数
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：使用限制，满x元可用
	 */
	public void setUseRestrict(Integer useRestrict) {
		this.useRestrict = useRestrict;
	}
	/**
	 * 获取：使用限制，满x元可用
	 */
	public Integer getUseRestrict() {
		return useRestrict;
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
	 * 设置：领取人
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：领取人
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：状态0=未使用，1=已使用
	 */
	public void setUse(Integer use) {
		this.use = use;
	}
	/**
	 * 获取：状态0=未使用，1=已使用
	 */
	public Integer getUse() {
		return use;
	}
	/**
	 * 设置：使用订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：使用订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：使用时间
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	/**
	 * 获取：使用时间
	 */
	public Date getUseTime() {
		return useTime;
	}
	public Integer getMacId() {
		return macId;
	}
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	public Integer getProudctId() {
		return proudctId;
	}
	public void setProudctId(Integer proudctId) {
		this.proudctId = proudctId;
	}
}
