package com.lebaoxun.manager.sys.entity;

import java.io.Serializable;

/**
 * 
 * @author F.Bin
 *
 */
public class SysUserButton implements Serializable{
	private static final long serialVersionUID = -1524060558645054617L;
	private String buttonCode;
	private String buttonName;
	private String buttonCss;
	public String getButtonCode() {
		return buttonCode;
	}
	public void setButtonCode(String buttonCode) {
		this.buttonCode = buttonCode;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public String getButtonCss() {
		return buttonCss;
	}
	public void setButtonCss(String buttonCss) {
		this.buttonCss = buttonCss;
	}
	
}
