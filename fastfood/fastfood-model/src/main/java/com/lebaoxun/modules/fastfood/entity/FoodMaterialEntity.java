package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐品原料表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@TableName("food_material")
public class FoodMaterialEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 原料名称
	 */
	private String name;
	/**
	 * 原料分类ID
	 */
	private Integer catId;
	/**
	 * 状态0=未上架，1=已上架
	 */
	private Integer status;
	/**
	 * 规格
	 */
	private String spec;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 修改人
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
	 * 设置：原料名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：原料名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：原料分类ID
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	/**
	 * 获取：原料分类ID
	 */
	public Integer getCatId() {
		return catId;
	}
	/**
	 * 设置：状态0=未上架，1=已上架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=未上架，1=已上架
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * 获取：规格
	 */
	public String getSpec() {
		return spec;
	}
	/**
	 * 设置：图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：图片
	 */
	public String getPicture() {
		return picture;
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
	 * 设置：修改人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：修改人
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
