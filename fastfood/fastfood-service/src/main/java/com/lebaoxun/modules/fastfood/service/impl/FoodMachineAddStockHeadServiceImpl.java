package com.lebaoxun.modules.fastfood.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.manager.sys.entity.SysUserEntity;
import com.lebaoxun.modules.fastfood.entity.*;
import com.lebaoxun.modules.fastfood.service.*;
import com.lebaoxun.modules.manager.service.ISysUserService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineAddStockHeadDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("foodMachineAddStockHeadService")
public class FoodMachineAddStockHeadServiceImpl extends ServiceImpl<FoodMachineAddStockHeadDao, FoodMachineAddStockHeadEntity> implements FoodMachineAddStockHeadService {
    @Autowired
    private FoodMachineAddStockOrderService foodMachineAddStockOrderService;
    @Autowired
    private FoodMachineAisleService foodMachineAisleService;
    @Autowired
    private FoodMachineProActivityRefService foodMachineProActivityRefService;

    @Resource
    private IRabbitmqSender rabbitmqSender;
    @Resource
    private FoodMachineService foodMachineService;
    @Autowired
    private ISysUserService sysUserService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAddStockHeadEntity> page = this.selectPage(
                new Query<FoodMachineAddStockHeadEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAddStockHeadEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor=Exception.class,propagation = Propagation.REQUIRES_NEW)
    public boolean acceptPickingOrder(Long userId, FoodMachineAddStockHeadEntity foodMachineAddStockHead) {
        foodMachineAddStockHead.setUpdateBy(userId);
        boolean ret=this.updateById(foodMachineAddStockHead);
        //配货员确认配货后
        //需要更新货道库存
        //需要核实机器货道产品有没有发生变化，如有则需要删除货道关联的产品活动
        if (ret){
            EntityWrapper<FoodMachineAddStockOrderEntity> entityWrapper=new EntityWrapper<FoodMachineAddStockOrderEntity>();
            entityWrapper.eq("head_id",foodMachineAddStockHead.getId());
            List<FoodMachineAddStockOrderEntity> stockOrderEntities=foodMachineAddStockOrderService.selectList(entityWrapper);
            if(stockOrderEntities==null||stockOrderEntities.get(0).getId()==null)
                return false;
            Integer macId=stockOrderEntities.get(0).getMacId();
            //查询机器货道产品明细
            EntityWrapper<FoodMachineAisleEntity> aisleEntityWrapper=new EntityWrapper<FoodMachineAisleEntity>();
            aisleEntityWrapper.eq("mac_id",macId);
            List<FoodMachineAisleEntity> aisleList=foodMachineAisleService.selectList(aisleEntityWrapper);
            Map<Integer,FoodMachineAisleEntity> aisleMap= Maps.newHashMap();
            aisleList.forEach(e->{
                aisleMap.put(e.getId(),e);
            });
            List<FoodMachineAisleEntity> dbAisleList= Lists.newArrayList();
            for (FoodMachineAddStockOrderEntity stockOrder:stockOrderEntities){
                FoodMachineAisleEntity cAisle=aisleMap.get(stockOrder.getAisleId());
                //如有则需要删除货道关联的产品活动、更新货道关联产品
                if (cAisle.getProductId()!=stockOrder.getProductId()){
                    EntityWrapper<FoodMachineProActivityRefEntity> proActWrapper=new EntityWrapper<FoodMachineProActivityRefEntity>();
                    proActWrapper.eq("mac_id",macId);
                    proActWrapper.eq("aisle_id",stockOrder.getAisleId());
                    foodMachineProActivityRefService.delete(proActWrapper);

                    //更新货道产品
                    FoodMachineAisleEntity aisleEntity=new FoodMachineAisleEntity();
                    aisleEntity.setId(cAisle.getId());
                    aisleEntity.setPrice(stockOrder.getPrice());
                    aisleEntity.setProductId(stockOrder.getProductId());
                    aisleEntity.setProductCatId(stockOrder.getLineCatId());
                    aisleEntity.setStock(stockOrder.getAdd());
                    dbAisleList.add(aisleEntity);
                }else {
                    FoodMachineAisleEntity aisleEntity=new FoodMachineAisleEntity();
                    aisleEntity.setId(cAisle.getId());
                    aisleEntity.setPrice(stockOrder.getPrice());
                    aisleEntity.setProductCatId(stockOrder.getLineCatId());
                    aisleEntity.setStock(cAisle.getStock()+stockOrder.getAdd());
                    dbAisleList.add(aisleEntity);
                }
            }
            foodMachineAisleService.updateBatchById(dbAisleList);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor=Exception.class,propagation = Propagation.REQUIRES_NEW)
    public ResponseMessage createPickingOreder(Long adminId, List<FoodMachineAddStockOrderEntity> addStockOrderList) {
        int i=0;
        if (addStockOrderList!=null&&addStockOrderList.size()>0){
            FoodMachineAddStockHeadEntity headEntity = new FoodMachineAddStockHeadEntity();
            for (FoodMachineAddStockOrderEntity e:addStockOrderList){
                if (i==0) {
                    //首先判断补货、配货单是否在进行中
                    EntityWrapper<FoodMachineAddStockHeadEntity> entityWrapper=new EntityWrapper<FoodMachineAddStockHeadEntity>();
                    entityWrapper.eq("mac_id",e.getMacId());
                    entityWrapper.eq("status",0);
                    List<FoodMachineAddStockHeadEntity> cList=this.baseMapper.selectList(entityWrapper);
                    if (cList!=null&&cList.size()>0){
                        return ResponseMessage.error("600002","机器["+e.getMacId()
                                +"],货道["+e.getX()+"-"+e.getY()+"已有补货单在进行中！]!");
                    }

                    //插入主表
                    headEntity.setMacId(e.getMacId());
                    headEntity.setStatus(0);
                    headEntity.setPerformer(e.getPerformer());
                    headEntity.setSendOrderTime(new Date());
                    headEntity.setCreateBy(adminId);
                    headEntity.setUpdateBy(adminId);
                    this.baseMapper.insert(headEntity);
                    //发送短信提醒
                    this.sendMsg(e.getMacId());
                    i++;
                }
                //插入子表
                e.setStatus(0);
                e.setCreateBy(adminId);
                e.setCreateTime(new Date());
                e.setUpdateTime(new Date());
                e.setHeadId(headEntity.getId());
                foodMachineAddStockOrderService.insert(e);
            }
        }else {
            return ResponseMessage.error("600001","未收到补货数据!");
        }
        return ResponseMessage.ok();
    }

    @Override
    public ResponseMessage sendMsg(Integer macId) {
        EntityWrapper<FoodMachineAddStockHeadEntity> entityWrapper=new EntityWrapper<FoodMachineAddStockHeadEntity>();
        entityWrapper.eq("mac_id",macId);
        entityWrapper.eq("status",0);
        FoodMachineAddStockHeadEntity sendMsgStockHeadEntity=this.selectOne(entityWrapper);
        if (sendMsgStockHeadEntity!=null&&sendMsgStockHeadEntity.getId()>0){
            //查询机器编号
            FoodMachineEntity foodMachineEntity=foodMachineService.selectById(macId);
            //查询用户手机号
            SysUserEntity userEntity=sysUserService.findByUserId(sendMsgStockHeadEntity.getPerformer());
            if (userEntity==null||userEntity.getUserId()==0)
                return ResponseMessage.error("60003","用户不存在，短信发送失败！");
            Map<String,String> message = Maps.newHashMap();
            message.put("mobile", userEntity.getMobile());
            String content="您好，"+userEntity.getUsername()
                    +",你有新的配货单，请注意查收" +
                    "机器编号["+foodMachineEntity.getImei()+"]";
            message.put("content", content);
            message.put("m_id", macId+"");
            message.put("m_type", "STOCK_ORDER");
            rabbitmqSender.sendContractDirect("sms.early.warn.queue",
                    new Gson().toJson(message));
            return ResponseMessage.ok();
        }
        return ResponseMessage.error("60002","短信发送失败！");
    }

}
