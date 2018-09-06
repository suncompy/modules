package com.lebaoxun.modules.fastfood.entity.operate;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖记录表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@TableName("operate_prize_get_log")
public class OperatePrizeGetLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id=0;
	/**
	 * 奖品ID
	 */
	private Integer prizeId;
	/**
	 * 奖品名称
	 */
	private String name;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 通道ID
	 */
	private Integer aisle;
	/**
	 * 权重，概率
	 */
	private Integer weight;
	/**
	 * 中奖人
	 */
	private Long userId;
	/**
	 * 中奖时间
	 */
	private Date createTime;
	/**
	 * 状态0=未领取，1=已领取
	 */
	private Integer status;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 分组，区分奖品位置
	 */
	private String group;

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
	 * 设置：奖品ID
	 */
	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}
	/**
	 * 获取：奖品ID
	 */
	public Integer getPrizeId() {
		return prizeId;
	}
	/**
	 * 设置：奖品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：奖品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：通道ID
	 */
	public void setAisle(Integer aisle) {
		this.aisle = aisle;
	}
	/**
	 * 获取：通道ID
	 */
	public Integer getAisle() {
		return aisle;
	}
	/**
	 * 设置：权重，概率
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * 获取：权重，概率
	 */
	public Integer getWeight() {
		return weight;
	}
	/**
	 * 设置：中奖人
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：中奖人
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：中奖时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：中奖时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：状态0=未领取，1=已领取
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=未领取，1=已领取
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：分组，区分奖品位置
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * 获取：分组，区分奖品位置
	 */
	public String getGroup() {
		return group;
	}
}
