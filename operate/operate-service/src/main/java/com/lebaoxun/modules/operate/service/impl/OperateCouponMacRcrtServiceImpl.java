package com.lebaoxun.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.operate.dao.OperateCouponMacRcrtDao;
import com.lebaoxun.modules.operate.entity.OperateCouponMacRcrtEntity;
import com.lebaoxun.modules.operate.service.OperateCouponMacRcrtService;


@Service("operateCouponMacRcrtService")
public class OperateCouponMacRcrtServiceImpl extends ServiceImpl<OperateCouponMacRcrtDao, OperateCouponMacRcrtEntity> implements OperateCouponMacRcrtService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateCouponMacRcrtEntity> page = this.selectPage(
                new Query<OperateCouponMacRcrtEntity>(params).getPage(),
                new EntityWrapper<OperateCouponMacRcrtEntity>()
        );

        return new PageUtils(page);
    }

}
