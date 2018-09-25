package com.lebaoxun.modules.fastfood.task;

import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 机器货道定时任务
 * Created by lenovo on 2018/9/25.
 */
@Component
public class FoodMachineAisleListener {
    private FoodMachineAisleService foodMachineAisleService;

    /**
     * 派单晚上12点跑定时任务，如果机器存在状态为派单中配货单，则将该机器的货道库存清零
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void clearMachineAisleStock() {
        foodMachineAisleService.clearMachineAisleStock();
    }
}
