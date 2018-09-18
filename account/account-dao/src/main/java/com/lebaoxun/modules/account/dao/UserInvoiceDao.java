package com.lebaoxun.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.account.entity.UserInvoiceEntity;

/**
 * 发票信息
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 14:13:24
 */
@Mapper
public interface UserInvoiceDao extends BaseMapper<UserInvoiceEntity> {
	
	List<UserInvoiceEntity> findByUserId(@Param("userId")Long userId);
}
