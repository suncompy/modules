package com.lebaoxun.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 现金红包
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */
@TableName("operate_red_packed")
public class OperateRedPackedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id = 0;
	/**
	 * 红包名称
	 */
	private String name;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 金额
	 */
	private Integer money;
	/**
	 * 激活码
	 */
	private String cdkey;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 领取人
	 */
	private Long userId;
	/**
	 * 状态0=未激活，1=已激活，2=已使用
	 */
	private Integer use;
	/**
	 * 使用订单号
	 */
	private String orderNo;
	/**
	 * 使用时间
	 */
	private Date useTime;

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
	 * 设置：红包名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：红包名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(Integer money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public Integer getMoney() {
		return money;
	}
	/**
	 * 设置：激活码
	 */
	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}
	/**
	 * 获取：激活码
	 */
	public String getCdkey() {
		return cdkey;
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
	 * 设置：领取人
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：领取人
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：状态0=未激活，1=已激活，2=已使用
	 */
	public void setUse(Integer use) {
		this.use = use;
	}
	/**
	 * 获取：状态0=未激活，1=已激活，2=已使用
	 */
	public Integer getUse() {
		return use;
	}
	/**
	 * 设置：使用订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：使用订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：使用时间
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	/**
	 * 获取：使用时间
	 */
	public Date getUseTime() {
		return useTime;
	}
}
