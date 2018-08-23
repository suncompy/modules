package com.lebaoxun.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.AdPosEntity;

import java.util.Map;

/**
 * 广告位
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-22 23:06:49
 */
public interface AdPosService extends IService<AdPosEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

