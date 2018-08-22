package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 取餐机
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_machine")
public class FoodMachineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	/**
	 * 机器编号
	 */
	private String imei;
	/**
	 * 通信端口，IP
	 */
	private String host;
	/**
	 * 所在地区
	 */
	private String areaCode;
	/**
	 *省市区拼接地址
	 */
	@TableField(exist=false)
	private String joinAddress;
	/**
	 *详细地址
	 */
	private String address;
	/**
	 * 地图经纬度
	 */
	private String pos;
	/**
	 * 机器公告
	 */
	private String notice;
	/**
	 * 机器名称
	 */
	private String name;
	/**
	 * 机器分类名称
	 */
	@TableField(exist=false)
	private String catName;
	/**
	 * 机器分类
	 */
	private Integer catId;
	private Integer managerId;
	@TableField(exist=false)
	private String managerName;
	/**
	 * 合作公司ID
	 */
	private Integer partnerId;
	/**
	 * 状态0=正常，-1=维修
	 */
	private Integer status;
	/**
	 * 补货员
	 */
	private Long replenishMan;
	/**
	 * 配货员
	 */
	private Long pickingMan;
	/**
	 * 维修员
	 */
	private Long maintenanceMan;
	/**
	 * 描述
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
	
	@TableField(exist=false)
	private List<String> activitys;
	@TableField(exist=false)
	private List<FoodMachineProductCatEntity> productCats;

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
	 * 设置：机器编号
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * 获取：机器编号
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * 设置：通信端口，IP
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * 获取：通信端口，IP
	 */
	public String getHost() {
		return host;
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
	 * 设置：地图经纬度
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}
	/**
	 * 获取：地图经纬度
	 */
	public String getPos() {
		return pos;
	}
	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * 设置：机器公告
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}
	/**
	 * 获取：机器公告
	 */
	public String getNotice() {
		return notice;
	}
	/**
	 * 设置：机器分类
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	/**
	 * 获取：机器分类
	 */
	public Integer getCatId() {
		return catId;
	}
	/**
	 * 设置：合作公司ID
	 */
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	/**
	 * 获取：合作公司ID
	 */
	public Integer getPartnerId() {
		return partnerId;
	}
	/**
	 * 设置：状态0=正常，-1=维修
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=正常，-1=维修
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：补货员
	 */
	public void setReplenishMan(Long replenishMan) {
		this.replenishMan = replenishMan;
	}
	/**
	 * 获取：补货员
	 */
	public Long getReplenishMan() {
		return replenishMan;
	}
	/**
	 * 设置：配货员
	 */
	public void setPickingMan(Long pickingMan) {
		this.pickingMan = pickingMan;
	}
	/**
	 * 获取：配货员
	 */
	public Long getPickingMan() {
		return pickingMan;
	}
	/**
	 * 设置：维修员
	 */
	public void setMaintenanceMan(Long maintenanceMan) {
		this.maintenanceMan = maintenanceMan;
	}
	/**
	 * 获取：维修员
	 */
	public Long getMaintenanceMan() {
		return maintenanceMan;
	}
	/**
	 * 设置：描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：描述
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

	public String getJoinAddress() {
		return joinAddress;
	}

	public void setJoinAddress(String joinAddress) {
		this.joinAddress = joinAddress;
	}

	public String getAddress() {
		return address;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getActivitys() {
		return activitys;
	}
	public void setActivitys(List<String> activitys) {
		this.activitys = activitys;
	}
	public List<FoodMachineProductCatEntity> getProductCats() {
		return productCats;
	}
	public void setProductCats(List<FoodMachineProductCatEntity> productCats) {
		this.productCats = productCats;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
