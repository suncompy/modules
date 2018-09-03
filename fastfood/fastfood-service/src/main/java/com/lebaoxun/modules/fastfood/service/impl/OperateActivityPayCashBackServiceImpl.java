package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.operate.OperateActivityPayCashBackDao;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;
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

	@Override
	public OperateActivityPayCashBackEntity findUnderwayActivity() {
		// TODO Auto-generated method stub
		return this.baseMapper.findUnderwayActivity();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateById(OperateActivityPayCashBackEntity entity) {
		OperateActivityPayCashBackEntity underway = this.findUnderwayActivity();
		if(underway == null || (underway.getUse() == 0 && entity.getUse() == 1)){//如果重新开放
			this.baseMapper.closeAllActivity();
			entity.setId(0);
			this.baseMapper.insert(entity);
			return true;
		}
		return super.updateById(entity);
	}
}
