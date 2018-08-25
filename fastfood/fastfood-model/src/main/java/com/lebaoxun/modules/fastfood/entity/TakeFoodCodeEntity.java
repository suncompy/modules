package com.lebaoxun.modules.fastfood.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 取餐码对应订单
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public class TakeFoodCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private String orderNo;
	private Long timeStamp;
	public TakeFoodCodeEntity(){}
	public TakeFoodCodeEntity(String orderNo,Long timeStamp){
		this.orderNo=orderNo;
		this.timeStamp=timeStamp;
	}
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
