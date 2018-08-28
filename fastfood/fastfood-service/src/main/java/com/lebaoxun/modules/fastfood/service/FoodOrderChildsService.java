package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 订单明细表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodOrderChildsService extends IService<FoodOrderChildsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 扫码查询订单信息
     * @param orderId
     * @return
     */
    public List<Map<String, Object>> getSweeptCodeOrderChildsInfo(String orderId);

    long updateTakeNum(String orderId,String macId,String productId);
}

