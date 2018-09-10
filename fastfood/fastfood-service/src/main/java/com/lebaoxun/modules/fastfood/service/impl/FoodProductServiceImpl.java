package com.lebaoxun.modules.fastfood.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodProductDao;
import com.lebaoxun.modules.fastfood.dao.FoodProductMaterialRcrtDao;
import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductMaterialRcrtEntity;
import com.lebaoxun.modules.fastfood.service.FoodProductService;


@Service("foodProductService")
public class FoodProductServiceImpl extends ServiceImpl<FoodProductDao, FoodProductEntity> implements FoodProductService {

	@Resource
	private FoodProductMaterialRcrtDao foodProductMaterialRcrtDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	String name = (String)params.get("name");
        Page<FoodProductEntity> page = this.selectPage(
                new Query<FoodProductEntity>(params).getPage(),
                new EntityWrapper<FoodProductEntity>()
                .like(StringUtils.isNotBlank(name), "name", name)
        );

        return new PageUtils(page);
    }
    
	@Override
	public List<FoodProductEntity> findProductInfoByParams(Map<String, Object> params){
		return this.selectByMap(params);
	}

	@Override
	public List<FoodProductEntity> findAllProductByCat(Integer catId) {
		return this.selectList(new EntityWrapper<FoodProductEntity>().eq("product_cat_id", catId));
	}
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean insert(FoodProductEntity entity) {
    	// TODO Auto-generated method stub
    	List<Integer> materialIds = entity.getMaterialIds();
    	
    	int period = (int) ((entity.getDownTime().getTime()  - entity.getUpTime().getTime()) / (1000*3600*24));
    	
    	entity.setPeriod(period);
    	boolean result = super.insert(entity);
    	if(materialIds != null){
    		for(Integer materialId : materialIds){
    			FoodProductMaterialRcrtEntity fpcmr = new FoodProductMaterialRcrtEntity();
    			fpcmr.setMaterialId(materialId);
    			fpcmr.setProductId(entity.getId());
    			foodProductMaterialRcrtDao.insert(fpcmr);
    		}
    	}
    	return result;
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean updateById(FoodProductEntity entity) {
    	List<Integer> materialIds = entity.getMaterialIds();
    	
    	List<FoodProductMaterialRcrtEntity> fpcmrs = foodProductMaterialRcrtDao.selectList(new EntityWrapper<FoodProductMaterialRcrtEntity>().eq("product_id", entity.getId()));
    	
    	List<Integer> l1 = new ArrayList<Integer>();
		if(fpcmrs != null){
			for(FoodProductMaterialRcrtEntity fpcmcr : fpcmrs){
				l1.add(fpcmcr.getMaterialId());
			}
		}
    	
    	List<Integer> l2 = new ArrayList<Integer>();
    	l2.addAll(l1);
    	for(Integer materialId : materialIds){
    		if(!l1.contains(materialId)){
    			FoodProductMaterialRcrtEntity fpcmr = new FoodProductMaterialRcrtEntity();
    			fpcmr.setMaterialId(materialId);
    			fpcmr.setProductId(entity.getId());
    			foodProductMaterialRcrtDao.insert(fpcmr);
    		}else{
    			l2.remove(materialId);
    		}
    	}
    	
    	for(Integer materialId : l2){
    		foodProductMaterialRcrtDao.delete(new EntityWrapper<FoodProductMaterialRcrtEntity>().eq("material_id", materialId).eq("product_id", entity.getId()));
    	}
    	int period = (int) ((entity.getDownTime().getTime()  - entity.getUpTime().getTime()) / (1000*3600*24));
    	
    	entity.setPeriod(period);
    	return super.updateById(entity);
    }
    
    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
    	// TODO Auto-generated method stub
    	List<FoodProductEntity> list = this.baseMapper.selectBatchIds(idList);
    	for(FoodProductEntity fp:list){
    		foodProductMaterialRcrtDao.delete(new EntityWrapper<FoodProductMaterialRcrtEntity>().eq("product_id", fp.getId()));
    	}
    	return super.deleteBatchIds(idList);
    }
}
