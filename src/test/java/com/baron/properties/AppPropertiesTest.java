package com.baron.properties;

import com.baron.program.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jason on 2017/6/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class AppPropertiesTest {
    @Autowired
    AppProperties appProperties;

    @Test
    public void testProperties() {
        assertNotNull(appProperties);
    }
}