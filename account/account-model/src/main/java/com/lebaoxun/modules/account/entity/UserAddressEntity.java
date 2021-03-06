package com.lebaoxun.modules.account.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户地址
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-06-26 10:20:24
 */
@TableName("user_address")
public class UserAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 默认首选
	 */
	private String defaultFlag;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 所在地区
	 */
	private String areaCode;
	/**
	 * 地图经纬度
	 */
	private String pos;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 地址别名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 经度
	 */
	private Double lat;
	/**
	 * 纬度
	 */
	private Double lng;
	
	@TableField(exist=false)
	private String fulladdress;

	/**
	 * 设置：
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public long getId() {
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
	 * 设置：默认首选
	 */
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	/**
	 * 获取：默认首选
	 */
	public String getDefaultFlag() {
		return defaultFlag;
	}
	/**
	 * 设置：收货人
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 * 获取：收货人
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：所在地区
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：所在地区
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：地址别名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：地址别名
	 */
	public String getName() {
		return name;
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
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getFulladdress() {
		return fulladdress;
	}
	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
}
