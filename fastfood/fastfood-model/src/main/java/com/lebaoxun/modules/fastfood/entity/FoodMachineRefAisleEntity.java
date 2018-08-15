package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 取餐机通道产品关联，前端显示
 * 
 */
public class FoodMachineRefAisleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 机器分类
	 */
	private Integer macId;
	/**
	 * 分类ID
	 */
	private Integer productCatId;
	/**
	 * 产品Id
	 */
	private Integer productId;
	/**
	 * 价格
	 */
	private Float price;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 分类名称
	 */
	private String catName;

	public FoodMachineRefAisleEntity() {
		id = 0;
	}

	public Integer getMacId() {
		return macId;
	}

	public void setMacId(Integer macId) {
		this.macId = macId;
	}

	public Integer getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(Integer productCatId) {
		this.productCatId = productCatId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
}
