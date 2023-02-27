package com.exchangeinformant.configuration;

import com.exchangeinformant.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    @RefreshScope
    public StockService stockServiceSearcher(@Value("${quotes.supplier}") String serviceName) {
        return (StockService) context.getBean(serviceName);
    }
}
