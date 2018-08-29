package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeEntity;

/**
 * 抽奖奖品配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
public interface OperatePrizeService extends IService<OperatePrizeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<OperatePrizeEntity> findPrizeByGroup(String group);
}

