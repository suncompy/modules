package com.lebaoxun.modules.mall.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 分类表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-07-12 19:57:12
 */
@TableName("mall_category")
public class MallCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类ID
	 */
	@TableId
	private long id;
	/**
	 * 父菜单名称
	 */
	@TableField(exist=false)
	private String parentName;
	/**
	 * 父分类ID
	 */
	private Long parentId;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类图标
	 */
	private String icon;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 目录类型 2=二级目录/1=一级目录/0=总目录
	 */
	private Integer type;
	/**
	 * 状态 1=显示/0=隐藏
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 备注信息
	 */
	private String remarks;

	/**
	 * 设置：分类ID
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 获取：分类ID
	 */
	public long getId() {
		return id;
	}
	/**
	 * 设置：父分类ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父分类ID
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：分类图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：分类图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：目录类型 2=二级目录/1=一级目录/0=总目录
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：目录类型 2=二级目录/1=一级目录/0=总目录
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：状态 1=显示/0=隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1=显示/0=隐藏
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemarks() {
		return remarks;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
