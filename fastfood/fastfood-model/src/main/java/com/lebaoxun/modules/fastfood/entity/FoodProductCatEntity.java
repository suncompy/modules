package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 餐品分类
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_product_cat")
public class FoodProductCatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 子分类数量
	 */
	@TableField(exist=false)
	private Integer productTotal;
	/**
	 * 状态0=不展示，1=展示
	 */
	private Integer status;
	/**
	 * 关键词
	 */
	private String keyword;
	/**
	 * 描述
	 */
	private String descr;
	/**
	 * 图标
	 */
	private String icon;
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

	private String selectedImg;

	private String nSelectedImg;

	public String getSelectedImg() {
		return selectedImg;
	}

	public void setSelectedImg(String selectedImg) {
		this.selectedImg = selectedImg;
	}

	public String getnSelectedImg() {
		return nSelectedImg;
	}

	public void setnSelectedImg(String nSelectedImg) {
		this.nSelectedImg = nSelectedImg;
	}

	/**
	 * 分类ID
	 */
	@TableField(exist=false)
	private List<Integer> materialCatIds;

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
	 * 设置：子分类数量
	 */
	public void setProductTotal(Integer productTotal) {
		this.productTotal = productTotal;
	}
	/**
	 * 获取：子分类数量
	 */
	public Integer getProductTotal() {
		return productTotal;
	}
	/**
	 * 设置：状态0=不展示，1=展示
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=不展示，1=展示
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：关键词
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * 获取：关键词
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * 设置：描述
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	/**
	 * 获取：描述
	 */
	public String getDescr() {
		return descr;
	}
	/**
	 * 设置：图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：图标
	 */
	public String getIcon() {
		return icon;
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
	public List<Integer> getMaterialCatIds() {
		return materialCatIds;
	}
	public void setMaterialCatIds(List<Integer> materialCatIds) {
		this.materialCatIds = materialCatIds;
	}
}
