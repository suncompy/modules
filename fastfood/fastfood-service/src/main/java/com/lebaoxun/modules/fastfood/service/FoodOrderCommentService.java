package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentEntity;

/**
 * 评价表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
public interface FoodOrderCommentService extends IService<FoodOrderCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    void publish(Long userId, FoodOrderCommentEntity comment);
	
	List<FoodOrderCommentEntity> selectByMacId(Integer macId);
	
	FoodOrderCommentEntity selectLastByMacId(Integer macId);
}

