package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 取餐机进货派单
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_machine_add_stock_order")
public class FoodMachineAddStockOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 通道ID
	 */
	private Integer aisleId;
	/**
	 * 进货数
	 */
	private Integer add;
	/**
	 * 产品ID
	 */
	private Integer productId;
	/**
	 * 执行人
	 */
	private Long performer;
	/**
	 * 状态0=进行中，1=已完成
	 */
	private Integer status;
	/**
	 * 类别0=补货，1=配货
	 */
	private Integer type;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	@TableField(exist=false)
	private FoodMachineAisleEntity aisle;

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
	 * 设置：机器ID
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：机器ID
	 */
	public Integer getMacId() {
		return macId;
	}
	/**
	 * 设置：进货数
	 */
	public void setAdd(Integer add) {
		this.add = add;
	}
	/**
	 * 获取：进货数
	 */
	public Integer getAdd() {
		return add;
	}
	/**
	 * 设置：产品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：执行人
	 */
	public void setPerformer(Long performer) {
		this.performer = performer;
	}
	/**
	 * 获取：执行人
	 */
	public Long getPerformer() {
		return performer;
	}
	/**
	 * 设置：状态0=进行中，1=已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=进行中，1=已完成
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：类别0=补货，1=配货
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类别0=补货，1=配货
	 */
	public Integer getType() {
		return type;
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
	public Integer getAisleId() {
		return aisleId;
	}
	public void setAisleId(Integer aisleId) {
		this.aisleId = aisleId;
	}
	public FoodMachineAisleEntity getAisle() {
		return aisle;
	}
	public void setAisle(FoodMachineAisleEntity aisle) {
		this.aisle = aisle;
	}
}
