package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityPayCashBackDao;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityPayCashBackEntity;
import com.lebaoxun.modules.fastfood.service.OperateActivityPayCashBackService;


@Service("operateActivityPayCashBackService")
public class OperateActivityPayCashBackServiceImpl extends ServiceImpl<OperateActivityPayCashBackDao, OperateActivityPayCashBackEntity> implements OperateActivityPayCashBackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateActivityPayCashBackEntity> page = this.selectPage(
                new Query<OperateActivityPayCashBackEntity>(params).getPage(),
                new EntityWrapper<OperateActivityPayCashBackEntity>()
        );

        return new PageUtils(page);
    }

}
