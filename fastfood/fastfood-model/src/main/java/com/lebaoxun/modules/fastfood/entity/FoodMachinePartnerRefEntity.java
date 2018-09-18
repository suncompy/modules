package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 机器合作商关联表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
@TableName("food_machine_partner_ref")
public class FoodMachinePartnerRefEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id = 0;
	/**
	 * 绑定机器
	 */
	private Integer partnerId;
	/**
	 * 绑定机器
	 */
	private Integer macId;
	/**
	 * 0=合作公司，1=加盟商
	 */
	private Integer flag;
	/**
	 * 绑定时间
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
	 * 设置：绑定机器
	 */
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	/**
	 * 获取：绑定机器
	 */
	public Integer getPartnerId() {
		return partnerId;
	}
	/**
	 * 设置：绑定机器
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：绑定机器
	 */
	public Integer getMacId() {
		return macId;
	}
	/**
	 * 设置：0=合作公司，1=加盟商
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	/**
	 * 获取：0=合作公司，1=加盟商
	 */
	public Integer getFlag() {
		return flag;
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
}
