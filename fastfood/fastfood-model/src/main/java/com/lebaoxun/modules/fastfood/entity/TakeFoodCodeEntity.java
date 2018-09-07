package com.lebaoxun.modules.fastfood.entity;

import java.io.Serializable;

/**
 * 取餐码对应订单
 * 
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public class TakeFoodCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long orderId;
    private String orderNo;
	private Long timeStamp;
	public TakeFoodCodeEntity(){}
	public TakeFoodCodeEntity(Long orderId,String orderNo,Long timeStamp){
		this.orderId=orderId;
		this.orderNo=orderNo;
		this.timeStamp=timeStamp;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
