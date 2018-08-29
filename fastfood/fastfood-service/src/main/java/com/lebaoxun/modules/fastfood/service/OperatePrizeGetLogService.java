package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeGetLogEntity;

/**
 * 抽奖记录表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
public interface OperatePrizeGetLogService extends IService<OperatePrizeGetLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 查询我的抽奖记录
     * @param userId
     * @param status
     * @param size
     * @param offset
     * @return
     */
    List<OperatePrizeGetLogEntity> findLogByUserId(Long userId,Integer status,Integer size,Integer offset);
    
    /**
     * 抽奖接口
     * @param userId
     * @param group
     * @return
     */
    ResponseMessage draw(Long userId, String group);
    
}

