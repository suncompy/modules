package com.lebaoxun.modules.account.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.account.entity.UserInvoiceEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 发票信息
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 14:13:24
 */
public interface UserInvoiceService extends IService<UserInvoiceEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<UserInvoiceEntity> findByUserId(Long userId);
}

