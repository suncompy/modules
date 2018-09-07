package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 取餐机分类通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineCatAisleService extends IService<FoodMachineCatAisleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> findAisleInfoByCatId(Integer catId);

    void saveFoodMachineCatAisle(FoodMachineCatAisleEntity entity);
}

