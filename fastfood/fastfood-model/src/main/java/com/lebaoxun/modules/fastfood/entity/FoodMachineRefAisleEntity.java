package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 取餐机通道产品关联，前端显示
 * 
 */
public class FoodMachineRefAisleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 机器渠道id集
	 */
	private List<String> aisleIds;
	/**
	 * 机器id
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

	/**
	 * 货道坐标
	 */
	private String aisleXy;

	/**
	 * 货道容量
	 */
	private String aisleSize;

	public List<String> getAisleIds() {
		return aisleIds;
	}

	public void setAisleIds(List<String> aisleIds) {
		this.aisleIds = aisleIds;
	}

	public String getAisleXy() {
		return aisleXy;
	}

	public void setAisleXy(String aisleXy) {
		this.aisleXy = aisleXy;
	}

	public String getAisleSize() {
		return aisleSize;
	}

	public void setAisleSize(String aisleSize) {
		this.aisleSize = aisleSize;
	}

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
