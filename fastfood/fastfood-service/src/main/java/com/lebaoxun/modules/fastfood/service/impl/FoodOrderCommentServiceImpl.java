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
	public void publish(Long userId, FoodOrderCommentEntity comment) {
		// TODO Auto-generated method stub
		FoodOrderEntity order = foodOrderDao.selectById(comment.getOrderId());
		if(order == null){
			throw new I18nMessageException("60007","订单不存在");
		}
		
		if(order.getOrderStatus() != 2){
			throw new I18nMessageException("60017","订单已评价");
		}
		
		//修改订单状态为已评价
		order.setOrderStatus(3);
		foodOrderDao.updateById(order);
		
		comment.setUserId(userId);
		comment.setMacId(order.getMacId());
		comment.setOrderId(order.getId());
		comment.setOrderNo(order.getOrderNo());
		comment.setPraises(0);
		comment.setType(0);
		comment.setCreateTime(new Date());
		this.baseMapper.insert(comment);
		List<FoodOrderCommentImageEntity> images = comment.getPicImgs();
		if(images != null){
			for(FoodOrderCommentImageEntity commentImg : images){
				commentImg.setStatus(1);
				commentImg.setCommentId(comment.getId());
				foodOrderCommentImageDao.insert(commentImg);
			}
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
