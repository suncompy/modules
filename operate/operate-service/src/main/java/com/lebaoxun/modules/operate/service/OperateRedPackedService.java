package com.lebaoxun.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateRedPackedEntity;

import java.util.Map;

/**
 * 现金红包
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
public interface OperateRedPackedService extends IService<OperateRedPackedEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

