package com.baron.pipeline;

import com.baron.pool.Pool;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Jason on 2017/6/13.
 */
@Component
@Scope("prototype")
public class DefaultPipeline implements Pipeline {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void process(ResultItems resultItems, Task task) {
        return;
    }
}
