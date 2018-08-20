package com.lebaoxun.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.operate.dao.OperateActivityKeepDiscountDao;
import com.lebaoxun.modules.operate.entity.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.operate.service.OperateActivityKeepDiscountService;


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
