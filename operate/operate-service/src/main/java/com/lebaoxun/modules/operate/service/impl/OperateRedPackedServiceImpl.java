package com.lebaoxun.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.operate.dao.OperateRedPackedDao;
import com.lebaoxun.modules.operate.entity.OperateRedPackedEntity;
import com.lebaoxun.modules.operate.service.OperateRedPackedService;


@Service("operateRedPackedService")
public class OperateRedPackedServiceImpl extends ServiceImpl<OperateRedPackedDao, OperateRedPackedEntity> implements OperateRedPackedService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateRedPackedEntity> page = this.selectPage(
                new Query<OperateRedPackedEntity>(params).getPage(),
                new EntityWrapper<OperateRedPackedEntity>()
        );

        return new PageUtils(page);
    }

}
