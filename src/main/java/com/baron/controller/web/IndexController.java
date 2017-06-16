package com.baron.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Baron.Chen on 2017/6/3.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/html/index.html";
    }
}
