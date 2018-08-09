package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐品表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@TableName("food_product")
public class FoodProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 产品食材，以;分割
	 */
	private String materials;
	/**
	 * 参考价格
	 */
	private Float showPrice;
	/**
	 * 参考价格
	 */
	private Integer period;
	/**
	 * 下架提醒时间
	 */
	private Integer remind;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 总库存
	 */
	private Integer totalStock;
	/**
	 * 总库存预警值
	 */
	private Integer stockAlertVal;
	/**
	 * 计量单位
	 */
	private String unit;
	/**
	 * 主食重量
	 */
	private String zsWeight;
	/**
	 * 菜品重量
	 */
	private String cpWeight;
	/**
	 * 是否加热
	 */
	private Integer warmFlag;
	/**
	 * 加热时间
	 */
	private Integer warmTime;
	/**
	 * 推荐指数
	 */
	private Integer hotNum;
	/**
	 * 标签0=无，1=新品，2=推荐
	 */
	private Integer label;
	/**
	 * 状态0=下架，1=上架
	 */
	private Integer status;
	/**
	 * 上架时间
	 */
	private Date upTime;
	/**
	 * 下架时间
	 */
	private Date downTime;
	/**
	 * 关键词
	 */
	private String keyword;
	/**
	 * 描述
	 */
	private String descr;
	/**
	 * 备注
	 */
	private String remark;
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
	 * 设置：产品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：产品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：产品食材，以;分割
	 */
	public void setMaterials(String materials) {
		this.materials = materials;
	}
	/**
	 * 获取：产品食材，以;分割
	 */
	public String getMaterials() {
		return materials;
	}
	/**
	 * 设置：参考价格
	 */
	public void setShowPrice(Float showPrice) {
		this.showPrice = showPrice;
	}
	/**
	 * 获取：参考价格
	 */
	public Float getShowPrice() {
		return showPrice;
	}
	/**
	 * 设置：参考价格
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	/**
	 * 获取：参考价格
	 */
	public Integer getPeriod() {
		return period;
	}
	/**
	 * 设置：下架提醒时间
	 */
	public void setRemind(Integer remind) {
		this.remind = remind;
	}
	/**
	 * 获取：下架提醒时间
	 */
	public Integer getRemind() {
		return remind;
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
	 * 设置：总库存
	 */
	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}
	/**
	 * 获取：总库存
	 */
	public Integer getTotalStock() {
		return totalStock;
	}
	/**
	 * 设置：总库存预警值
	 */
	public void setStockAlertVal(Integer stockAlertVal) {
		this.stockAlertVal = stockAlertVal;
	}
	/**
	 * 获取：总库存预警值
	 */
	public Integer getStockAlertVal() {
		return stockAlertVal;
	}
	/**
	 * 设置：计量单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：计量单位
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：主食重量
	 */
	public void setZsWeight(String zsWeight) {
		this.zsWeight = zsWeight;
	}
	/**
	 * 获取：主食重量
	 */
	public String getZsWeight() {
		return zsWeight;
	}
	/**
	 * 设置：菜品重量
	 */
	public void setCpWeight(String cpWeight) {
		this.cpWeight = cpWeight;
	}
	/**
	 * 获取：菜品重量
	 */
	public String getCpWeight() {
		return cpWeight;
	}
	/**
	 * 设置：是否加热
	 */
	public void setWarmFlag(Integer warmFlag) {
		this.warmFlag = warmFlag;
	}
	/**
	 * 获取：是否加热
	 */
	public Integer getWarmFlag() {
		return warmFlag;
	}
	/**
	 * 设置：加热时间
	 */
	public void setWarmTime(Integer warmTime) {
		this.warmTime = warmTime;
	}
	/**
	 * 获取：加热时间
	 */
	public Integer getWarmTime() {
		return warmTime;
	}
	/**
	 * 设置：推荐指数
	 */
	public void setHotNum(Integer hotNum) {
		this.hotNum = hotNum;
	}
	/**
	 * 获取：推荐指数
	 */
	public Integer getHotNum() {
		return hotNum;
	}
	/**
	 * 设置：标签0=无，1=新品，2=推荐
	 */
	public void setLabel(Integer label) {
		this.label = label;
	}
	/**
	 * 获取：标签0=无，1=新品，2=推荐
	 */
	public Integer getLabel() {
		return label;
	}
	/**
	 * 设置：状态0=下架，1=上架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0=下架，1=上架
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：上架时间
	 */
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	/**
	 * 获取：上架时间
	 */
	public Date getUpTime() {
		return upTime;
	}
	/**
	 * 设置：下架时间
	 */
	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}
	/**
	 * 获取：下架时间
	 */
	public Date getDownTime() {
		return downTime;
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
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
