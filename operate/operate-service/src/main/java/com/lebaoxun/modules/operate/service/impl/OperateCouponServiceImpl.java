package com.lebaoxun.modules.operate.service.impl;

import com.lebaoxun.commons.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.operate.dao.OperateCouponDao;
import com.lebaoxun.modules.operate.entity.OperateCouponEntity;
import com.lebaoxun.modules.operate.service.OperateCouponService;


@Service("operateCouponService")
public class OperateCouponServiceImpl extends ServiceImpl<OperateCouponDao, OperateCouponEntity> implements OperateCouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name=(String) params.get("name");
        Page<OperateCouponEntity> page = this.selectPage(
                new Query<OperateCouponEntity>(params).getPage(),
                new EntityWrapper<OperateCouponEntity>().like(StringUtils.isNotBlank(name),"name", name)
        );

        return new PageUtils(page);
    }

    @Override
    public List<OperateCouponEntity> findByMacId(Integer macId,Long userId) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findByMacId(macId,userId);
    }
}
