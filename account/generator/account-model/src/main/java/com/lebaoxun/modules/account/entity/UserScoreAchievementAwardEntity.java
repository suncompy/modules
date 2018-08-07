package com.lebaoxun.modules.account.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户任务表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
@TableName("user_score_achievement_award")
public class UserScoreAchievementAwardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 任务码
	 */
	private String code;
	/**
	 * 任务名
	 */
	private String name;
	/**
	 * 任务分类
	 */
	private Integer cat;
	/**
	 * 任务分类名
	 */
	private String catName;
	/**
	 * 任务条件
	 */
	private String condi;
	/**
	 * 任务触发点
	 */
	private String action;
	/**
	 * 任务奖励
	 */
	private Integer bonus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 创建人
	 */
	private Long updateBy;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	private Integer delFlag;

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
	 * 设置：任务码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：任务码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：任务名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：任务名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：任务分类
	 */
	public void setCat(Integer cat) {
		this.cat = cat;
	}
	/**
	 * 获取：任务分类
	 */
	public Integer getCat() {
		return cat;
	}
	/**
	 * 设置：任务分类名
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * 获取：任务分类名
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * 设置：任务条件
	 */
	public void setCondi(String condi) {
		this.condi = condi;
	}
	/**
	 * 获取：任务条件
	 */
	public String getCondi() {
		return condi;
	}
	/**
	 * 设置：任务触发点
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * 获取：任务触发点
	 */
	public String getAction() {
		return action;
	}
	/**
	 * 设置：任务奖励
	 */
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	/**
	 * 获取：任务奖励
	 */
	public Integer getBonus() {
		return bonus;
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
	 * 设置：最后更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：是否删除  -1：已删除  0：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  0：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
}
