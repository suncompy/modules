package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityFirstOrderDao;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.fastfood.service.OperateActivityFirstOrderService;


@Service("operateActivityFirstOrderService")
public class OperateActivityFirstOrderServiceImpl extends ServiceImpl<OperateActivityFirstOrderDao, OperateActivityFirstOrderEntity> implements OperateActivityFirstOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateActivityFirstOrderEntity> page = this.selectPage(
                new Query<OperateActivityFirstOrderEntity>(params).getPage(),
                new EntityWrapper<OperateActivityFirstOrderEntity>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public OperateActivityFirstOrderEntity findUnderwayActivity() {
    	return this.baseMapper.findUnderwayActivity();
    }

}
