package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAddStockOrderDao;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.dao.FoodProductDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockOrderService;


@Service("foodMachineAddStockOrderService")
public class FoodMachineAddStockOrderServiceImpl extends ServiceImpl<FoodMachineAddStockOrderDao, FoodMachineAddStockOrderEntity> implements FoodMachineAddStockOrderService {

	@Resource
	private FoodMachineAisleDao foodMachineAisleDao;
	
	@Resource
	private FoodProductDao foodProductDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAddStockOrderEntity> page = this.selectPage(
                new Query<FoodMachineAddStockOrderEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAddStockOrderEntity>()
        );

        return new PageUtils(page);
    }

	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean insert(FoodMachineAddStockOrderEntity entity) {
    	// TODO Auto-generated method stub
    	Integer aisleId = entity.getAisleId();
    	FoodMachineAisleEntity aisle = foodMachineAisleDao.selectById(aisleId);
    	if(aisle == null){
    		throw new I18nMessageException("60013","货道不存在");
    	}
    	int size = aisle.getSize();
    	int stock = aisle.getStock();
    	if(entity.getAdd() > (size - stock)){
    		throw new I18nMessageException("60014","补货数不对");
    	}
    	
    	FoodProductEntity product = foodProductDao.selectById(aisle.getProductId());
    	int totalStock = product.getTotalStock() - entity.getAdd();
    	if(totalStock < 0){
    		throw new I18nMessageException("60015","餐品库存不足");
    	}
    	product.setTotalStock(totalStock);
    	foodProductDao.updateById(product);
    	
    	entity.setMacId(aisle.getMacId());
    	entity.setProductId(aisle.getProductId());
    	entity.setStatus(0);
    	entity.setCreateTime(new Date());
    	return super.insert(entity);
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean updateById(FoodMachineAddStockOrderEntity entity) {
    	// TODO Auto-generated method stub
    	if(entity.getStatus() == 1){//补货完成
    		Integer aisleId = entity.getAisleId();
        	FoodMachineAisleEntity aisle = foodMachineAisleDao.selectById(aisleId);
        	int stock = aisle.getStock() + entity.getAdd();
        	stock = stock > aisle.getSize() ? aisle.getSize() : stock;
        	aisle.setStock(stock);
        	
        	foodMachineAisleDao.updateById(aisle);
    	}
    	return super.updateById(entity);
    }
	@Override
	public List<Map<String, Object>> queryReplenishManList(String userName, String mobile, String createTime) {
		return this.baseMapper.queryReplenishManList(userName,mobile,createTime);
	}

	@Override
	public List<Map<String, Object>> queryPickingManList(String userName, String mobile, String createTime) {
		return this.baseMapper.queryPickingManList(userName,mobile,createTime);
	}

	@Override
	public List<Map<String, Object>> queryPickingOrderList(String status, String macInfo, String id, String sendOrderTime) {
		return this.baseMapper.queryPickingOrderList(status,macInfo,id,sendOrderTime);
	}
}
