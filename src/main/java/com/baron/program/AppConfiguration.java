package com.baron.program;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Baron.Chen on 2017/6/15.
 */
@Configuration
@EnableAutoConfiguration
public class AppConfiguration {
    @Bean
    public void setApplicationContext(ApplicationContext applicationContext) {
        AppCache.put(AppConstants.CACHE_SPRING_APPLICATON_CONTEXT, applicationContext);
    }
}
