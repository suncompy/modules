package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineCatAisleDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatAisleService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("foodMachineCatAisleService")
public class FoodMachineCatAisleServiceImpl extends ServiceImpl<FoodMachineCatAisleDao, FoodMachineCatAisleEntity> implements FoodMachineCatAisleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineCatAisleEntity> page = this.selectPage(
                new Query<FoodMachineCatAisleEntity>(params).getPage(),
                new EntityWrapper<FoodMachineCatAisleEntity>()
        );

        return new PageUtils(page);
    }

    public List<Map<String,Object>> findAisleInfoByCatId(Integer catId){
        return this.baseMapper.findAisleInfoByCatId(catId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void saveFoodMachineCatAisle(FoodMachineCatAisleEntity entity) {
        this.insert(entity);
    }
}
