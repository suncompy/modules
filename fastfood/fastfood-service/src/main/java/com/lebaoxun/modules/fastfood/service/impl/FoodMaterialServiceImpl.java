package com.lebaoxun.modules.fastfood.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMaterialDao;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.service.FoodMaterialService;


@Service("foodMaterialService")
public class FoodMaterialServiceImpl extends ServiceImpl<FoodMaterialDao, FoodMaterialEntity> implements FoodMaterialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String idOrName = (String)params.get("idOrName"),
    			catId = (String)params.get("catId"),
    				status = (String)params.get("status");
    	boolean isF = StringUtils.isNotBlank(idOrName);
    	boolean isF2 = isF && StringUtils.isNumeric(idOrName);
        Page<FoodMaterialEntity> page = this.selectPage(
                new Query<FoodMaterialEntity>(params).getPage(),
                new EntityWrapper<FoodMaterialEntity>()
                .eq(StringUtils.isNotBlank(catId) && StringUtils.isNumeric(catId), "cat_id", catId)
                .eq(StringUtils.isNotBlank(status) && StringUtils.isNumeric(status), "status", status)
                .like(isF && !isF2, "name", idOrName)
                .like(isF2,"id",idOrName)
                .orderBy("create_time", false)
        );

        return new PageUtils(page);
    }

}
