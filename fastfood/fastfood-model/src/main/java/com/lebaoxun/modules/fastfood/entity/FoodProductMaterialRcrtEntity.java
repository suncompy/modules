package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 餐品与原料类关联表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@TableName("food_product_material_rcrt")
public class FoodProductMaterialRcrtEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id = 0;
	
	/**
	 * 产品分类ID
	 */
	private Integer materialId;
	/**
	 * 产品分类ID
	 */
	private Integer productId;
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
