package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
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
public interface FoodMachineRepairOrderService extends IService<FoodMachineRepairOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> queryMaintenanceManList(String userName, String mobile, String createTime);

    List<Map<String,Object>> queryRepairOrderList(String status,String macInfo,String id,String sendOrderTime);
}

