package com.lebaoxun.modules.fastfood.entity;

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
	private Integer id;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * X坐标
	 */
	private Integer x;
	/**
	 * Y坐标
	 */
	private Integer y;
	/**
	 * Z坐标
	 */
	private Integer z;
	/**
	 * 总容量
	 */
	private Integer size;
	/**
	 * 库存
	 */
	private Integer stock;
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
	 * 设置：X坐标
	 */
	public void setX(Integer x) {
		this.x = x;
	}
	/**
	 * 获取：X坐标
	 */
	public Integer getX() {
		return x;
	}
	/**
	 * 设置：Y坐标
	 */
	public void setY(Integer y) {
		this.y = y;
	}
	/**
	 * 获取：Y坐标
	 */
	public Integer getY() {
		return y;
	}
	/**
	 * 设置：Z坐标
	 */
	public void setZ(Integer z) {
		this.z = z;
	}
	/**
	 * 获取：Z坐标
	 */
	public Integer getZ() {
		return z;
	}
	/**
	 * 设置：总容量
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	/**
	 * 获取：总容量
	 */
	public Integer getSize() {
		return size;
	}
	/**
	 * 设置：库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * 获取：库存
	 */
	public Integer getStock() {
		return stock;
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
}
