package com.baron.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Baron.Chen on 2017/6/8.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/error")
    public void testError() {
        throw new RuntimeException();
    }
}
