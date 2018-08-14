package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 取餐机分类通道
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_machine_cat_aisle")
public class FoodMachineCatAisleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	/**
	 * 机器分类
	 */
	private Integer catId;
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
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;

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
}
