package com.lebaoxun.modules.account.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 发票信息
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 14:13:24
 */
@TableName("user_invoice")
public class UserInvoiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id = 0;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 发票类型，0=个人，1=单位
	 */
	private Integer flag;
	/**
	 * 税号
	 */
	private String ird;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 发票抬头
	 */
	private String title;
	/**
	 * 创建时间
	 */
	private Date createTime;

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
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：发票类型，0=个人，1=单位
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	/**
	 * 获取：发票类型，0=个人，1=单位
	 */
	public Integer getFlag() {
		return flag;
	}
	/**
	 * 设置：税号
	 */
	public void setIrd(String ird) {
		this.ird = ird;
	}
	/**
	 * 获取：税号
	 */
	public String getIrd() {
		return ird;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：发票抬头
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：发票抬头
	 */
	public String getTitle() {
		return title;
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
}
