package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;


@Service("foodMachineService")
public class FoodMachineServiceImpl extends ServiceImpl<FoodMachineDao, FoodMachineEntity> implements FoodMachineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineEntity> page = this.selectPage(
                new Query<FoodMachineEntity>(params).getPage(),
                new EntityWrapper<FoodMachineEntity>()
        );

        return new PageUtils(page);
    }

}
