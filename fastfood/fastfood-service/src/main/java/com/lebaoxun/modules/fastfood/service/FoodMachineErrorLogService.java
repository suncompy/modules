package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineErrorLogEntity;

import java.util.Map;

/**
 * 终端机器错误日志表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-23 19:35:01
 */
public interface FoodMachineErrorLogService extends IService<FoodMachineErrorLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

