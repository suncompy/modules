package com.lebaoxun.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.operate.dao.AdPosDao;
import com.lebaoxun.modules.operate.entity.AdPosEntity;
import com.lebaoxun.modules.operate.service.AdPosService;


@Service("adPosService")
public class AdPosServiceImpl extends ServiceImpl<AdPosDao, AdPosEntity> implements AdPosService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AdPosEntity> page = this.selectPage(
                new Query<AdPosEntity>(params).getPage(),
                new EntityWrapper<AdPosEntity>()
        );

        return new PageUtils(page);
    }

}
