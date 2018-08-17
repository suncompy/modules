package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:23
 */
public interface FoodMachineActivityRefService extends IService<FoodMachineActivityRefEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FoodMachineActivityRefEntity> findMacActivityListByMacId(Integer macId);
}

