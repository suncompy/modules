package com.lebaoxun.modules.account.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 等级特权表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
@TableName("user_level_privilege")
public class UserLevelPrivilegeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 账户等级
	 */
	private Integer level;
	/**
	 * 累计充值金额
	 */
	private Integer payAmount;
	/**
	 * 计算档位1=1月，2=2月以此类推
	 */
	private Integer unit;
	/**
	 * 特权折扣率
	 */
	private Integer dis;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	private Integer delFlag;

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
	 * 设置：账户等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：账户等级
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：累计充值金额
	 */
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：累计充值金额
	 */
	public Integer getPayAmount() {
		return payAmount;
	}
	/**
	 * 设置：计算档位1=1月，2=2月以此类推
	 */
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	/**
	 * 获取：计算档位1=1月，2=2月以此类推
	 */
	public Integer getUnit() {
		return unit;
	}
	/**
	 * 设置：特权折扣率
	 */
	public void setDis(Integer dis) {
		this.dis = dis;
	}
	/**
	 * 获取：特权折扣率
	 */
	public Integer getDis() {
		return dis;
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
	/**
	 * 设置：是否删除  -1：已删除  0：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  0：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
}
