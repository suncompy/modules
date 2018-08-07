package com.lebaoxun.modules.account.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 用户任务
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 20:01:34
 *
 */
@TableName("user_score_achievement_award")
public class UserScoreAchievementAwardEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4669362430008012827L;
	@TableId
	private long id;
	/**
	 * 任务码
	 */
	private String code;
	/**
	 * 任务名
	 */
	private String name;
	/**
	 * 分类
	 */
	private Integer cat;
	/**
	 * 分类名
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
	 * 修改人
	 */
	private Long updateBy;
	/**
	 * 是否删除
	 */
	private Integer delFlag;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCat() {
		return cat;
	}
	public void setCat(Integer cat) {
		this.cat = cat;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCondi() {
		return condi;
	}
	public void setCondi(String condi) {
		this.condi = condi;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getBonus() {
		return bonus;
	}
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
