package com.lebaoxun.modules.fastfood.dao;

import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 维修派单表
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineRepairOrderDao extends BaseMapper<FoodMachineRepairOrderEntity> {
    List<Map<String,Object>> queryMaintenanceManList(@Param("userName")String userName,
                                                 @Param("mobile")String mobile,
                                                 @Param("createTime")String createTime);
    List<Map<String,Object>> queryRepairOrderList(@Param("status")String status,
                                                   @Param("macInfo")String macInfo,
                                                   @Param("id")String id,
                                                   @Param("sendOrderTime")String sendOrderTime);
}
