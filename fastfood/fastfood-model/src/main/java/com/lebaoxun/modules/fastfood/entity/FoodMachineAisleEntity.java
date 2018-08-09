package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 取餐机通道
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@TableName("food_machine_aisle")
public class FoodMachineAisleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 机器分类
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
	 * 产品ID
	 */
	private Integer productId;
	/**
	 * 产品分类
	 */
	private Integer productCatId;
	/**
	 * 创建时间
	 */
	private Date createTime;
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
	 * 设置：机器分类
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：机器分类
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
	 * 设置：产品分类
	 */
	public void setProductCatId(Integer productCatId) {
		this.productCatId = productCatId;
	}
	/**
	 * 获取：产品分类
	 */
	public Integer getProductCatId() {
		return productCatId;
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
