package com.baron.program;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by Baron.Chen on 2017/6/9.
 */
@Configuration
public class MongoConfig {
    public IMongodConfig iMongodConfig() throws IOException {
        IMongodConfig iMongodConfig = new MongodConfigBuilder()
                .build();
        return iMongodConfig;
    }

    public IRuntimeConfig iRuntimeConfig() {
        IRuntimeConfig iRuntimeConfig = new RuntimeConfigBuilder()
                .build();
        return iRuntimeConfig;
    }
}
