package com.lebaoxun.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
@TableName("operate_coupon")
public class OperateCouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 总发行量
	 */
	private Integer total;
	/**
	 * 每人限领
	 */
	private Integer peoNumRestrict;
	/**
	 * 0=现金，1=折扣
	 */
	private Integer type;
	/**
	 * 折扣额度或现金数
	 */
	private Float amount;
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
	 * 领取开始时间
	 */
	private Date startGetTime;
	/**
	 * 领取结束时间
	 */
	private Date endGetTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 编辑人
	 */
	private Long updateBy;
	/**
	 * 修改时间
	 */
	private Date updateTime;

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
	 * 设置：总发行量
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取：总发行量
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * 设置：每人限领
	 */
	public void setPeoNumRestrict(Integer peoNumRestrict) {
		this.peoNumRestrict = peoNumRestrict;
	}
	/**
	 * 获取：每人限领
	 */
	public Integer getPeoNumRestrict() {
		return peoNumRestrict;
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
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	/**
	 * 获取：折扣额度或现金数
	 */
	public Float getAmount() {
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
	 * 设置：领取开始时间
	 */
	public void setStartGetTime(Date startGetTime) {
		this.startGetTime = startGetTime;
	}
	/**
	 * 获取：领取开始时间
	 */
	public Date getStartGetTime() {
		return startGetTime;
	}
	/**
	 * 设置：领取结束时间
	 */
	public void setEndGetTime(Date endGetTime) {
		this.endGetTime = endGetTime;
	}
	/**
	 * 获取：领取结束时间
	 */
	public Date getEndGetTime() {
		return endGetTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
	 * 设置：编辑人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：编辑人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
