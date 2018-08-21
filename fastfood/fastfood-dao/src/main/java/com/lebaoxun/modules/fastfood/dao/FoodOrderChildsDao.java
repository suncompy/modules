package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单明细表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodOrderChildsDao extends BaseMapper<FoodOrderChildsEntity> {
    public List<Map<String, Object>> getSweeptCodeOrderChildsInfo(@Param("orderId") String orderId);
}
