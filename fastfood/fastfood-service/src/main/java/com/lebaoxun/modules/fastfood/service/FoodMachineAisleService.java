package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineAisleService extends IService<FoodMachineAisleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void refProductAndType(Long adminId,FoodMachineAisleEntity foodMachineAisle);

    List<FoodMachineRefAisleEntity> findMachineAisleListByMacId(Integer macId);
}

