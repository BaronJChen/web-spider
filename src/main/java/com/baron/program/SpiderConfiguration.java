package com.baron.program;

import com.baron.util.LogUtil;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jason on 2017/6/11.
 */
@Configuration
public class SpiderConfiguration {
    @Bean
    public CommandLineRunner cmd() {
        return (args) -> {
            Logger logger = LogUtil.getLogger(Application.class.getName());
            logger.info("this is a test");
        };
    }
}
