package com.lebaoxun.modules.operate.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateCouponEntity;

/**
 * 优惠券
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:33
 */
public interface OperateCouponService extends IService<OperateCouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<OperateCouponEntity> findByMacId(Integer macId,Long userId);
}

