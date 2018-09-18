package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 合作公司
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
@TableName("food_machine_partner")
public class FoodMachinePartnerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id = 0;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 公司地址
	 */
	private String address;
	/**
	 * 绑定人数
	 */
	private Integer bindAmount;
	/**
	 * 绑定时间
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
	 * 解绑时间
	 */
	private Date upbindTime;

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
	 * 设置：公司名称
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 设置：公司地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：公司地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：绑定人数
	 */
	public void setBindAmount(Integer bindAmount) {
		this.bindAmount = bindAmount;
	}
	/**
	 * 获取：绑定人数
	 */
	public Integer getBindAmount() {
		return bindAmount;
	}
	/**
	 * 设置：绑定时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：绑定时间
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
	 * 设置：解绑时间
	 */
	public void setUpbindTime(Date upbindTime) {
		this.upbindTime = upbindTime;
	}
	/**
	 * 获取：解绑时间
	 */
	public Date getUpbindTime() {
		return upbindTime;
	}
}
