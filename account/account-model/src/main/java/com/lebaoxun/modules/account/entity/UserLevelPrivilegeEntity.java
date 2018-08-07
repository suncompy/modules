package com.lebaoxun.modules.account.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 等级特权
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 20:01:34
 *
 */
@TableName("user_level_privilege")
public class UserLevelPrivilegeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8717458879809765093L;
	@TableId
	private long id;
	/**
	 * 账户等级
	 */
	private Integer level;
	/**
	 * 累计充值金额
	 */
	private Integer payAmount;
	/**
	 * 计算单位1=1月，2=2月以此类推
	 */
	private Integer unit;
	/**
	 * 特权折扣率%
	 */
	private Integer dis;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Integer createBy;
	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	private Integer delFlag;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Integer getDis() {
		return dis;
	}
	public void setDis(Integer dis) {
		this.dis = dis;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
