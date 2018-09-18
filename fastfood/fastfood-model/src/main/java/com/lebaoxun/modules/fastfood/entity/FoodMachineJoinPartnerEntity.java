package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 加盟商
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
@TableName("food_machine_join_partner")
public class FoodMachineJoinPartnerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id = 0;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 公司地址
	 */
	private String address;
	/**
	 * 加盟数量
	 */
	private Integer bindAmount;
	/**
	 * 管理员ID
	 */
	private Long adminId;
	/**
	 * -1=已拒绝，0=已办理，1=申请中
	 */
	private Integer status;
	/**
	 * 绑定时间
	 */
	private Date createTime;
	/**
	 * 申请用户
	 */
	private Long userId;
	/**
	 * 审核人
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
	 * 设置：姓名
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：姓名
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：职务
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：职务
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
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
	 * 设置：加盟数量
	 */
	public void setBindAmount(Integer bindAmount) {
		this.bindAmount = bindAmount;
	}
	/**
	 * 获取：加盟数量
	 */
	public Integer getBindAmount() {
		return bindAmount;
	}
	/**
	 * 设置：管理员ID
	 */
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	/**
	 * 获取：管理员ID
	 */
	public Long getAdminId() {
		return adminId;
	}
	/**
	 * 设置：-1=已拒绝，0=已办理，1=申请中
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：-1=已拒绝，0=已办理，1=申请中
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：申请用户
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：申请用户
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：审核人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：审核人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：修改时间
	 * @return
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
