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
import com.lebaoxun.modules.fastfood.dao.FoodOrderChildsDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderCommentDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderCommentImageDao;
import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentImageEntity;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderCommentService;


@Service("foodOrderCommentService")
public class FoodOrderCommentServiceImpl extends ServiceImpl<FoodOrderCommentDao, FoodOrderCommentEntity> implements FoodOrderCommentService {

	@Resource
	private FoodOrderChildsDao foodOrderChildsDao;
	
	@Resource
	private FoodOrderDao foodOrderDao;
	
	@Resource
	private FoodOrderCommentImageDao foodOrderCommentImageDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderCommentEntity> page = this.selectPage(
                new Query<FoodOrderCommentEntity>(params).getPage(),
                new EntityWrapper<FoodOrderCommentEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void publish(Long userId, Long childId,
			FoodOrderCommentEntity comment) {
		// TODO Auto-generated method stub
		FoodOrderChildsEntity orderChild = foodOrderChildsDao.selectById(childId);
		if(orderChild == null || orderChild.getStatus() == 1){
			throw new I18nMessageException("500");
		}
		
		int total = foodOrderChildsDao.selectCount(new EntityWrapper<FoodOrderChildsEntity>().eq("order_id", orderChild.getOrderId())),
				current = foodOrderChildsDao.selectCount(new EntityWrapper<FoodOrderChildsEntity>().eq("order_id", orderChild.getOrderId()).eq("status", 1));
		boolean isAll = total == current;//是否全部评价
		
		//修改订单状态为已评价
		orderChild.setStatus(1);
		foodOrderChildsDao.updateById(orderChild);
		
		if(isAll){
			FoodOrderEntity order = foodOrderDao.selectById(orderChild.getOrderId());
			order.setOrderStatus(3);
			foodOrderDao.updateById(order);
		}
		comment.setAisleId(orderChild.getAisleId());
		comment.setMacId(orderChild.getMacId());
		comment.setOrderChildId(orderChild.getId());
		comment.setPraises(0);
		comment.setProductId(orderChild.getProductId());
		comment.setOrderId(orderChild.getOrderId());
		comment.setType(0);
		comment.setCreateTime(new Date());
		this.baseMapper.save(comment);
		List<FoodOrderCommentImageEntity> images = comment.getPicImgs();
		for(FoodOrderCommentImageEntity commentImg : images){
			commentImg.setCommentId(comment.getId());
			foodOrderCommentImageDao.insert(commentImg);
		}
	}

	@Override
	public List<FoodOrderCommentEntity> selectByMacId(Integer macId) {
		// TODO Auto-generated method stub
		return this.baseMapper.selectByMacId(macId);
	}

	@Override
	public FoodOrderCommentEntity selectLastByMacId(Integer macId) {
		// TODO Auto-generated method stub
		return this.baseMapper.selectLastByMacId(macId);
	}

    
}
