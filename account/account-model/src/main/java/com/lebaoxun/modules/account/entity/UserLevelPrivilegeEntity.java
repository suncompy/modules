package com.lebaoxun.modules.account.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
	 * 等级名称
	 */
	private String levelName;
	/**
	 * 累计充值金额
	 */
	private BigDecimal exp;
	/**
	 * 计算单位1=1月，2=2月以此类推
	 */
	private Integer resetMonthUnit;
	/**
	 * 特权折扣率%
	 */
	private BigDecimal dis;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
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
	public BigDecimal getDis() {
		return dis;
	}
	public void setDis(BigDecimal dis) {
		this.dis = dis;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public BigDecimal getExp() {
		return exp;
	}
	public void setExp(BigDecimal exp) {
		this.exp = exp;
	}
	public Integer getResetMonthUnit() {
		return resetMonthUnit;
	}
	public void setResetMonthUnit(Integer resetMonthUnit) {
		this.resetMonthUnit = resetMonthUnit;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
