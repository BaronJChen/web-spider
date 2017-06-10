package com.baron.program;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

/**
 * Created by Baron.Chen on 2017/6/9.
 */
@Configuration
public class MongoConfig {
    @Bean
    public IMongodConfig iMongodConfig() throws IOException {
        IMongodConfig iMongodConfig = null;

        iMongodConfig = new MongodConfigBuilder()
                .version(Version.Main.DEVELOPMENT)
                .build();

        return iMongodConfig;
    }

    @Bean
    public CommandLineRunner cmd(MongoTemplate mongoTemplate) {
        return (args) -> {
            mongoTemplate.getCollection("spider").count();
        };
    }
}
