package com.lebaoxun.modules.fastfood.service.impl;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.manager.sys.entity.SysUserEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;
import com.lebaoxun.modules.manager.service.ISysUserService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineRepairOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineRepairOrderService;

import javax.annotation.Resource;


@Service("foodMachineRepairOrderService")
public class FoodMachineRepairOrderServiceImpl extends ServiceImpl<FoodMachineRepairOrderDao, FoodMachineRepairOrderEntity> implements FoodMachineRepairOrderService {
    @Resource
    private IRabbitmqSender rabbitmqSender;
    @Resource
    private FoodMachineService foodMachineService;
    @Autowired
    private ISysUserService sysUserService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineRepairOrderEntity> page = this.selectPage(
                new Query<FoodMachineRepairOrderEntity>(params).getPage(),
                new EntityWrapper<FoodMachineRepairOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> queryMaintenanceManList(String userName, String mobile, String createTime) {
        return this.baseMapper.queryMaintenanceManList(userName,mobile,createTime);
    }

    @Override
    public List<Map<String, Object>> queryRepairOrderList(String status, String macInfo, String id, String sendOrderTime) {
        return this.baseMapper.queryRepairOrderList(status,macInfo,id,sendOrderTime);
    }

    @Override
    public ResponseMessage sendMsg(Integer macId) {
        EntityWrapper<FoodMachineRepairOrderEntity> entityWrapper=new EntityWrapper<FoodMachineRepairOrderEntity>();
        entityWrapper.eq("mac_id",macId);
        entityWrapper.eq("status",0);
        FoodMachineRepairOrderEntity sendMsgRepairOrder=this.selectOne(entityWrapper);
        if (sendMsgRepairOrder!=null&&sendMsgRepairOrder.getId()>0){
            //查询机器编号
            FoodMachineEntity foodMachineEntity=foodMachineService.selectById(macId);
            //查询用户手机号
            SysUserEntity userEntity=sysUserService.findByUserId(sendMsgRepairOrder.getPerformer());
            if (userEntity==null||userEntity.getUserId()==0)
                return ResponseMessage.error("60003","用户不存在，短信发送失败！");
            Map<String,String> message = Maps.newHashMap();
            message.put("mobile", userEntity.getMobile());
            String content="您所管辖的机器编号为"+foodMachineEntity.getImei()+"的机器已发生故障，请及时查看派单任务进行维修";
//            String content="系统预警，编号["+foodMachineEntity.getImei()+"]机器出现故障，请立即查看！";
            message.put("content", content);
            message.put("m_id", macId+"");
            message.put("m_type", "MAC_ERROR");
            rabbitmqSender.sendContractDirect("sms.early.warn.queue",
                    new Gson().toJson(message));

            //发送给管理员
            userEntity=sysUserService.findByUserId(foodMachineEntity.getMaintenanceMan());
            if (userEntity==null||userEntity.getUserId()==0)
                return ResponseMessage.error("60003","用户不存在，短信发送失败！");
           message = Maps.newHashMap();
            message.put("mobile", userEntity.getMobile());
            content="您所管辖的机器编号为"+foodMachineEntity.getImei()+"的机器已发生故障，请及时查看并派单给维修员";
//            String content="系统预警，编号["+foodMachineEntity.getImei()+"]机器出现故障，请立即查看！";
            message.put("content", content);
            message.put("m_id", macId+"");
            message.put("m_type", "MAC_ERROR");
            rabbitmqSender.sendContractDirect("sms.early.warn.queue",
                    new Gson().toJson(message));
            return ResponseMessage.ok();
        }
        return ResponseMessage.error("60002","短信发送失败！");
    }

}
