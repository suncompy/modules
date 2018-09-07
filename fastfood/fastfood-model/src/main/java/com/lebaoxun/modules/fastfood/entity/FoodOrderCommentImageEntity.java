package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 评价图片表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
@TableName("food_order_comment_image")
public class FoodOrderCommentImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评价ID
	 */
	@TableId
	private Long id = 0l;
	/**
	 * 评价ID
	 */
	private Long commentId;
	/**
	 * 展示图片
	 */
	private String picImg;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 状态：1.显示；0.隐藏
	 */
	private Integer status;

	/**
	 * 设置：评价ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：评价ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：评价ID
	 */
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：评价ID
	 */
	public Long getCommentId() {
		return commentId;
	}
	/**
	 * 设置：展示图片
	 */
	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	/**
	 * 获取：展示图片
	 */
	public String getPicImg() {
		return picImg;
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
	 * 设置：状态：1.显示；0.隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1.显示；0.隐藏
	 */
	public Integer getStatus() {
		return status;
	}
}
