package com.exchangeinformant.configuration;

import com.exchangeinformant.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
@RefreshScope
public class SchedulingConfiguration implements SchedulingConfigurer {


    @Autowired
    public SchedulingConfiguration(@Qualifier("stockServiceSearcher") StockService  stockService) {
        this.stockService = stockService;
    }
    private final StockService stockService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Map<Runnable, Long> tasks = new HashMap<>();
        tasks.put(stockService::updateAllStocks,120000L);
        taskRegistrar.setFixedRateTasks(tasks);
    }
}
