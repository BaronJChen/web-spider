package com.baron.controller;

import com.baron.model.Exception;
import com.baron.program.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Baron.Chen on 2017/6/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ErrorControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void errorHandler() {
        Exception exception = restTemplate.getForObject("http://localhost/test/error", Exception.class);
        assertNotNull(exception);
    }
}