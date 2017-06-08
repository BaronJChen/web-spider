package com.baron.program;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongoConfig;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * Created by Jason on 2017/5/31.
 */
@SpringBootApplication
@ComponentScan("com.baron")
public class Application {

    public Application() {
        init();
    }

    private void init() {
        MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
        String ip = "localhost";
        int port = 26017;
        IMongodConfig iMongodConfig = null;

        try {
            iMongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(ip, port, false))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MongodExecutable mongodExecutable = mongodStarter.prepare(iMongodConfig);
        try {
            mongodExecutable.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mongodExecutable.stop();
        }
    }

    public static void main(String [] args) {
        SpringApplication.run(Application.class);
    }
}