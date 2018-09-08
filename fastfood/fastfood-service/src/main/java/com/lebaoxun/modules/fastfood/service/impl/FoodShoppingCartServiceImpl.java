package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodShoppingCartDao;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.FoodShoppingCartService;


@Service("foodShoppingCartService")
public class FoodShoppingCartServiceImpl extends ServiceImpl<FoodShoppingCartDao, FoodShoppingCartEntity> implements FoodShoppingCartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodShoppingCartEntity> page = this.selectPage(
                new Query<FoodShoppingCartEntity>(params).getPage(),
                new EntityWrapper<FoodShoppingCartEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public FoodShoppingCartEntity add(Long userId, Integer aisleId, Integer macId,
			Integer productId, Integer checkStatus, Integer buyNumber) {
		// TODO Auto-generated method stub
		FoodShoppingCartEntity cart = this.selectOne(new EntityWrapper<FoodShoppingCartEntity>().eq("aisle_id", aisleId).eq("user_id", userId));
		if(cart != null){
			return set(userId, cart.getCartId(), checkStatus, cart.getBuyNumber()+buyNumber);
		}
		cart = new FoodShoppingCartEntity();
		cart.setAisleId(aisleId);
		cart.setBuyNumber(buyNumber);
		cart.setCheckStatus(checkStatus);
		cart.setCreateTime(new Date());
		cart.setMacId(macId);
		cart.setProductId(productId);
		cart.setUserId(userId);
		this.baseMapper.insert(cart);
		return cart;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public FoodShoppingCartEntity set(Long userId, Long cartId,
			Integer checkStatus, Integer buyNumber) {
		// TODO Auto-generated method stub
		FoodShoppingCartEntity cart = this.selectOne(new EntityWrapper<FoodShoppingCartEntity>().eq("cart_id", cartId).eq("user_id", userId));
		if(cart == null){
			return cart;
		}
		cart.setCheckStatus(checkStatus);
		cart.setBuyNumber(buyNumber);
		cart.setUpdateTime(new Date());
		this.baseMapper.updateById(cart);
		return cart;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void remove(Long userId, Long cartId) {
		// TODO Auto-generated method stub
		this.delete(new EntityWrapper<FoodShoppingCartEntity>().eq("cart_id", cartId).eq("user_id", userId));
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void clear(Long userId) {
		// TODO Auto-generated method stub
		this.delete(new EntityWrapper<FoodShoppingCartEntity>().eq("user_id", userId));
	}

	@Override
	public List<Map<String,Object>> findByUser(Long userId) {
		// TODO Auto-generated method stub
		return this.baseMapper.findByUser(userId);
	}

    
}
