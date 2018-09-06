package com.lebaoxun.modules.fastfood.entity.operate;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖奖品配置
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@TableName("operate_prize")
public class OperatePrizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id=0;
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
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否默认，用户补足100%概率
	 */
	private Integer isDefault;
	/**
	 * 状态0=未激活，1=已激活
	 */
	private Integer status;
	/**
	 * 分组，区分奖品位置
	 */
	private String group;
	/**
	 * 消耗积分
	 */
	private Integer score;
	/**
	 * 排序值
	 */
	private Integer orderBy;

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
	 * 设置：是否默认，用户补足100%概率
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：是否默认，用户补足100%概率
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * 设置：状态0=未激活，1=已激活
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=未激活，1=已激活
	 */
	public Integer getStatus() {
		return status;
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
}
