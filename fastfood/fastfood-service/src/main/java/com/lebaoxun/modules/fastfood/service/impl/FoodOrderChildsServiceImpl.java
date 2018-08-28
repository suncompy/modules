package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodOrderChildsDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderChildsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("foodOrderChildsService")
public class FoodOrderChildsServiceImpl extends ServiceImpl<FoodOrderChildsDao, FoodOrderChildsEntity> implements FoodOrderChildsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderChildsEntity> page = this.selectPage(
                new Query<FoodOrderChildsEntity>(params).getPage(),
                new EntityWrapper<FoodOrderChildsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getSweeptCodeOrderChildsInfo(String orderId) {
        return this.baseMapper.getSweeptCodeOrderChildsInfo(orderId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public long updateTakeNum(String orderId, String macId, String productId) {
        return this.baseMapper.updateTakeNum(orderId,macId,productId);
    }

}
