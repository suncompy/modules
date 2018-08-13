package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 餐品分类与原料分类关联表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_product_cat_material_cat_rcrt")
public class FoodProductCatMaterialCatRcrtEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	
	/**
	 * 产品分类ID
	 */
	private Integer materialCatId;
	/**
	 * 产品分类ID
	 */
	private Integer productCatId;
	
	public Integer getMaterialCatId() {
		return materialCatId;
	}
	public void setMaterialCatId(Integer materialCatId) {
		this.materialCatId = materialCatId;
	}
	public Integer getProductCatId() {
		return productCatId;
	}
	public void setProductCatId(Integer productCatId) {
		this.productCatId = productCatId;
	}
	
}
