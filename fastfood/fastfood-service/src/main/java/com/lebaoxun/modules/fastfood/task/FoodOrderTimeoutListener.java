package com.lebaoxun.modules.fastfood.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.service.FoodOrderService;

@Component
public class FoodOrderTimeoutListener {
	
	@Resource
	private FoodOrderService foodOrderService;

	@Scheduled(cron = "0 */1 * * * ?")//每隔1分钟执行一次
	public void checkOrderTimeout() {
		foodOrderService.closeOrderByNopayAndTimeout();
	}
}
