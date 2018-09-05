package com.lebaoxun.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-05 14:15:42
 */
@TableName("ad_pos")
public class AdPosEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
@TableId
	private Integer id;
	/**
	 * 广告码
	 */
	private String posCode;
	/**
	 * 名称
	 */
	private String posName;
	/**
	 * 网络路径
	 */
	private String url;
	/**
	 * 文件类型 0.图片 1.视频
	 */
	private Integer fileType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createBy;
	/**
	 * 编辑人
	 */
	private Long updateBy;
	/**
	 * 广告类型：0普通广告，1屏保，2轮播图
	 */
	private Integer posType;

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
	 * 设置：广告码
	 */
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}
	/**
	 * 获取：广告码
	 */
	public String getPosCode() {
		return posCode;
	}
	/**
	 * 设置：名称
	 */
	public void setPosName(String posName) {
		this.posName = posName;
	}
	/**
	 * 获取：名称
	 */
	public String getPosName() {
		return posName;
	}
	/**
	 * 设置：网络路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：网络路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：文件类型 0.图片 1.视频
	 */
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：文件类型 0.图片 1.视频
	 */
	public Integer getFileType() {
		return fileType;
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
	 * 设置：编辑人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：编辑人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：广告类型：0普通广告，1屏保，2轮播图
	 */
	public void setPosType(Integer posType) {
		this.posType = posType;
	}
	/**
	 * 获取：广告类型：0普通广告，1屏保，2轮播图
	 */
	public Integer getPosType() {
		return posType;
	}
}
