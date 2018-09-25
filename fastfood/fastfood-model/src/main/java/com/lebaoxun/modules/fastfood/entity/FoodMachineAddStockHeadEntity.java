package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 取餐机进货派单主表
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-25 16:41:14
 */
@TableName("food_machine_add_stock_head")
public class FoodMachineAddStockHeadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id=0;
	/**
	 * 
	 */
	private Long performer;
	/**
	 * 
	 */
	private Date sendOrderTime;
	/**
	 * 
	 */
	private Date completeTime;
	/**
	 * 0.派单中 1.未派齐 2.已派齐
	 */
	private Integer status;
	/**
	 * 机器ID
	 */
	private Integer macId;
	/**
	 * 备注
	 */
	private String note;

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
	 * 设置：
	 */
	public void setPerformer(Long performer) {
		this.performer = performer;
	}
	/**
	 * 获取：
	 */
	public Long getPerformer() {
		return performer;
	}
	/**
	 * 设置：
	 */
	public void setSendOrderTime(Date sendOrderTime) {
		this.sendOrderTime = sendOrderTime;
	}
	/**
	 * 获取：
	 */
	public Date getSendOrderTime() {
		return sendOrderTime;
	}
	/**
	 * 设置：
	 */
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	/**
	 * 获取：
	 */
	public Date getCompleteTime() {
		return completeTime;
	}
	/**
	 * 设置：0.派单中 1.未派齐 2.已派齐
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0.派单中 1.未派齐 2.已派齐
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：备注
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注
	 */
	public String getNote() {
		return note;
	}
}
