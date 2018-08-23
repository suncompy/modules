package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityKeepDiscountDao;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.fastfood.service.OperateActivityKeepDiscountService;


@Service("operateActivityKeepDiscountService")
public class OperateActivityKeepDiscountServiceImpl extends ServiceImpl<OperateActivityKeepDiscountDao, OperateActivityKeepDiscountEntity> implements OperateActivityKeepDiscountService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OperateActivityKeepDiscountEntity> page = this.selectPage(
                new Query<OperateActivityKeepDiscountEntity>(params).getPage(),
                new EntityWrapper<OperateActivityKeepDiscountEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public OperateActivityKeepDiscountEntity findUnderwayActivity() {
		return this.baseMapper.findUnderwayActivity();
	}

}
