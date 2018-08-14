package com.lebaoxun.modules.fastfood.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMaterialCatDao;
import com.lebaoxun.modules.fastfood.dao.FoodMaterialDao;
import com.lebaoxun.modules.fastfood.dao.FoodProductCatDao;
import com.lebaoxun.modules.fastfood.dao.FoodProductCatMaterialCatRcrtDao;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatMaterialCatRcrtEntity;
import com.lebaoxun.modules.fastfood.service.FoodProductCatService;


@Service("foodProductCatService")
public class FoodProductCatServiceImpl extends ServiceImpl<FoodProductCatDao, FoodProductCatEntity> implements FoodProductCatService {

	@Resource
	private FoodMaterialCatDao foodMaterialCatDao;
	
	@Resource
	private FoodMaterialDao foodMaterialDao;
	
	@Resource
	private FoodProductCatMaterialCatRcrtDao foodProductCatMaterialCatRcrtDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodProductCatEntity> page = this.selectPage(
                new Query<FoodProductCatEntity>(params).getPage(),
                new EntityWrapper<FoodProductCatEntity>()
        );
        List<FoodProductCatEntity> records = page.getRecords();
        if(records != null){
        	for(FoodProductCatEntity fpc : records){
        		List<FoodProductCatMaterialCatRcrtEntity> fpcmcrs = foodProductCatMaterialCatRcrtDao.selectList(new EntityWrapper<FoodProductCatMaterialCatRcrtEntity>().eq("product_cat_id", fpc.getId()));
        		List<Integer> materialCatIds = new ArrayList<Integer>();
        		if(fpcmcrs != null){
        			for(FoodProductCatMaterialCatRcrtEntity fpcmcr : fpcmcrs){
        				materialCatIds.add(fpcmcr.getMaterialCatId());
        			}
        		}
        		
        		Integer total = 0;
            	for(Integer materialCatId : materialCatIds){
            		total += foodMaterialDao.selectCount(new EntityWrapper<FoodMaterialEntity>().eq("cat_id", materialCatId));
            	}
            	fpc.setProductTotal(total);
        		fpc.setMaterialCatIds(materialCatIds);
        	}
        }
        return new PageUtils(page);
    }
    
    @Override
    public FoodProductCatEntity selectById(Serializable id) {
    	// TODO Auto-generated method stub
    	FoodProductCatEntity fpc = super.selectById(id);
    	List<FoodProductCatMaterialCatRcrtEntity> fpcmcrs = foodProductCatMaterialCatRcrtDao.selectList(new EntityWrapper<FoodProductCatMaterialCatRcrtEntity>().eq("product_cat_id", fpc.getId()));
		List<Integer> materialCatIds = new ArrayList<Integer>();
		if(fpcmcrs != null){
			for(FoodProductCatMaterialCatRcrtEntity fpcmcr : fpcmcrs){
				materialCatIds.add(fpcmcr.getMaterialCatId());
			}
		}
		fpc.setMaterialCatIds(materialCatIds);
    	return fpc;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean insert(FoodProductCatEntity entity) {
    	// TODO Auto-generated method stub
    	List<Integer> materialCatIds = entity.getMaterialCatIds();
    	boolean result = super.insert(entity);
    	for(Integer materialCatId : materialCatIds){
    		FoodProductCatMaterialCatRcrtEntity fpcmcr = new FoodProductCatMaterialCatRcrtEntity();
    		fpcmcr.setMaterialCatId(materialCatId);
    		fpcmcr.setProductCatId(entity.getId());
    		foodProductCatMaterialCatRcrtDao.insert(fpcmcr);
    	}
    	return result;
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean updateById(FoodProductCatEntity entity) {
    	
    	List<Integer> materialCatIds = entity.getMaterialCatIds();
    	
    	List<FoodProductCatMaterialCatRcrtEntity> fpcmcrs = foodProductCatMaterialCatRcrtDao.selectList(new EntityWrapper<FoodProductCatMaterialCatRcrtEntity>().eq("product_cat_id", entity.getId()));
    	
    	List<Integer> l1 = new ArrayList<Integer>();
		if(fpcmcrs != null){
			for(FoodProductCatMaterialCatRcrtEntity fpcmcr : fpcmcrs){
				l1.add(fpcmcr.getMaterialCatId());
			}
		}
    	
    	List<Integer> l2 = new ArrayList<Integer>();
    	l2.addAll(l1);
    	for(Integer materialCatId : materialCatIds){
    		if(!l1.contains(materialCatId)){
    			FoodProductCatMaterialCatRcrtEntity fpcmcr = new FoodProductCatMaterialCatRcrtEntity();
    			fpcmcr.setMaterialCatId(materialCatId);
    			fpcmcr.setProductCatId(entity.getId());
    			foodProductCatMaterialCatRcrtDao.insert(fpcmcr);
    		}else{
    			l2.remove(materialCatId);
    		}
    	}
    	
    	for(Integer materialCatId : l2){
    		foodProductCatMaterialCatRcrtDao.delete(new EntityWrapper<FoodProductCatMaterialCatRcrtEntity>().eq("material_cat_id", materialCatId).eq("product_cat_id", entity.getId()));
    	}
    	return super.updateById(entity);
    }
    
    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
    	// TODO Auto-generated method stub
    	List<FoodProductCatEntity> list = this.baseMapper.selectBatchIds(idList);
    	for(FoodProductCatEntity fpc:list){
    		foodProductCatMaterialCatRcrtDao.delete(new EntityWrapper<FoodProductCatMaterialCatRcrtEntity>().eq("product_cat_id", fpc.getId()));
    	}
    	return super.deleteBatchIds(idList);
    }
    
    @Override
    public List<FoodMaterialEntity> queryFoodMaterialById(Integer id) {
    	// TODO Auto-generated method stub
    	
    	return foodMaterialDao.queryByProductCatId(id);
    }
}
