package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 取餐机进货派单
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineAddStockOrderDao extends BaseMapper<FoodMachineAddStockOrderEntity> {
    List<Map<String,Object>> queryReplenishManList(@Param("userName")String userName,
                                                   @Param("mobile")String mobile,
                                                   @Param("createTime")String createTime);

    List<Map<String,Object>> queryPickingManList(@Param("userName")String userName,
                                                   @Param("mobile")String mobile,
                                                   @Param("createTime")String createTime);

    List<Map<String,Object>> queryPickingOrderList(@Param("status")String status,
                                                   @Param("macInfo")String macInfo,
                                                   @Param("id")String id,
                                                   @Param("sendOrderTime")String sendOrderTime);

    List<Map<String,Object>> queryPickingLineByHeadId(@Param("status")String status,
                                                   @Param("headId")String headId);
}
