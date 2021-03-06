package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 订单退款表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_order_back")
public class FoodOrderBackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id = 0l;
	/**
	 * 订单编号,系统生成
	 */
	private String orderNo;
	/**
	 * 客服编号
	 */
	private String serviceNo;
	/**
	 * 客服姓名
	 */
	private String serviceNickname;
	/**
	 * 退款原因
	 */
	private String remark;
	/**
	 * 反馈时间
	 */
	private Date feedbackTime;
	/**
	 * 审核时间
	 */
	private Date checkTime;
	/**
	 * 处理情况
	 */
	private String checkRemark;
	/**
	 * 反馈用户
	 */
	private Long userId;
	/**
	 * 审核人
	 */
	private Long checkId;
	/**
	 * 退款金额
	 */
	private BigDecimal payAmount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 状态0=未处理，1=已处理
	 */
	private Integer status;
	/**
	 * 支付方式 1=在线支付，2=余额支付
	 */
	private Integer payType;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
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
	 * 设置：客服编号
	 */
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	/**
	 * 获取：客服编号
	 */
	public String getServiceNo() {
		return serviceNo;
	}
	/**
	 * 设置：客服姓名
	 */
	public void setServiceNickname(String serviceNickname) {
		this.serviceNickname = serviceNickname;
	}
	/**
	 * 获取：客服姓名
	 */
	public String getServiceNickname() {
		return serviceNickname;
	}
	/**
	 * 设置：退款原因
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：退款原因
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：反馈时间
	 */
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	/**
	 * 获取：反馈时间
	 */
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	/**
	 * 设置：审核时间
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * 获取：审核时间
	 */
	public Date getCheckTime() {
		return checkTime;
	}
	/**
	 * 设置：处理情况
	 */
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	/**
	 * 获取：处理情况
	 */
	public String getCheckRemark() {
		return checkRemark;
	}
	/**
	 * 设置：反馈用户
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：反馈用户
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：审核人
	 */
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	/**
	 * 获取：审核人
	 */
	public Long getCheckId() {
		return checkId;
	}
	/**
	 * 设置：退款金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：退款金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
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
	 * 设置：状态0=未处理，1=已处理
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=未处理，1=已处理
	 */
	public Integer getStatus() {
		return status;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
}
