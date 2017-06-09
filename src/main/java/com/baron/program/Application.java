package com.baron.program;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.store.*;
import de.flapdoodle.embed.process.config.store.DownloadConfigBuilder;
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
                    .version(Version.V3_3_1)
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