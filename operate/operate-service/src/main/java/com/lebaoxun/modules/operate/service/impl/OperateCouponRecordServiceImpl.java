package com.lebaoxun.modules.operate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.operate.dao.OperateCouponRecordDao;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.OperateCouponRecordService;


@Service("operateCouponRecordService")
public class OperateCouponRecordServiceImpl extends ServiceImpl<OperateCouponRecordDao, OperateCouponRecordEntity> implements OperateCouponRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateCouponRecordEntity> page = this.selectPage(
                new Query<OperateCouponRecordEntity>(params).getPage(),
                new EntityWrapper<OperateCouponRecordEntity>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public List<OperateCouponRecordEntity> findByUserId(Long userId,
    		Integer use,Integer flag,
    		Integer size, Integer offset) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findByUserId(userId,use,flag,size,offset);
    }

}
