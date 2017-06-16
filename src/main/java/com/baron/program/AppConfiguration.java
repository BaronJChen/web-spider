package com.baron.program;

import com.baron.properties.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Baron.Chen on 2017/6/15.
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfiguration {
    @Bean
    public CommandLineRunner setApplicationContext(ApplicationContext applicationContext) {
        return args -> AppCache.put(AppConstants.CACHE_SPRING_APPLICATON_CONTEXT, applicationContext);
    }
}
