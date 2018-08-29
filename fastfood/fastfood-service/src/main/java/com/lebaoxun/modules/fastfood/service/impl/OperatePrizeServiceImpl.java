package com.lebaoxun.modules.fastfood.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.operate.OperatePrizeDao;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeEntity;
import com.lebaoxun.modules.fastfood.service.OperatePrizeService;


@Service("operatePrizeService")
public class OperatePrizeServiceImpl extends ServiceImpl<OperatePrizeDao, OperatePrizeEntity> implements OperatePrizeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperatePrizeEntity> page = this.selectPage(
                new Query<OperatePrizeEntity>(params).getPage(),
                new EntityWrapper<OperatePrizeEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<OperatePrizeEntity> findPrizeByGroup(String group) {
		// TODO Auto-generated method stub
		return this.baseMapper.findPrizeByGroup(group);
	}

}
