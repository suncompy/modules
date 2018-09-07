package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 评价表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
@TableName("food_order_comment")
public class FoodOrderCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评价ID
	 */
	@TableId
	private Long id = 0l;
	/**
	 * 货道ID
	 */
	private Integer aisleId;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 通道ID
	 */
	private Integer productId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 用户头像
	 */
	private String headimgurl;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 评论星级：1,2,3,4,5
	 */
	private Integer star;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 好评数
	 */
	private Integer praises;
	/**
	 * 状态：1.显示；0.隐藏
	 */
	private Integer status;
	/**
	 * 评论类型：1,优质；0,普通
	 */
	private Integer type;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 子订单ID
	 */
	private Long orderChildId;
	
	@TableField(exist=false)
	private List<FoodOrderCommentImageEntity> picImgs;
	
	@TableField(exist=false)
	private FoodOrderChildsEntity child;

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
	 * 设置：货道ID
	 */
	public void setAisleId(Integer aisleId) {
		this.aisleId = aisleId;
	}
	/**
	 * 获取：货道ID
	 */
	public Integer getAisleId() {
		return aisleId;
	}
	/**
	 * 设置：机器ID
	 */
	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	/**
	 * 获取：机器ID
	 */
	public Integer getMacId() {
		return macId;
	}
	/**
	 * 设置：通道ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：通道ID
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：用户头像
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * 获取：用户头像
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	/**
	 * 设置：订单ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单ID
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：评论星级：1,2,3,4,5
	 */
	public void setStar(Integer star) {
		this.star = star;
	}
	/**
	 * 获取：评论星级：1,2,3,4,5
	 */
	public Integer getStar() {
		return star;
	}
	/**
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：好评数
	 */
	public void setPraises(Integer praises) {
		this.praises = praises;
	}
	/**
	 * 获取：好评数
	 */
	public Integer getPraises() {
		return praises;
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
	/**
	 * 设置：评论类型：1,优质；0,普通
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：评论类型：1,优质；0,普通
	 */
	public Integer getType() {
		return type;
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
	 * 设置：子订单ID
	 */
	public void setOrderChildId(Long orderChildId) {
		this.orderChildId = orderChildId;
	}
	/**
	 * 获取：子订单ID
	 */
	public Long getOrderChildId() {
		return orderChildId;
	}
	public List<FoodOrderCommentImageEntity> getPicImgs() {
		return picImgs;
	}
	public void setPicImgs(List<FoodOrderCommentImageEntity> picImgs) {
		this.picImgs = picImgs;
	}
	public FoodOrderChildsEntity getChild() {
		return child;
	}
	public void setChild(FoodOrderChildsEntity child) {
		this.child = child;
	}
}
