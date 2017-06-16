package com.baron.controller.api;

import com.baron.model.SpiderTask;
import com.baron.model.SpiderTemplate;
import com.baron.service.SpiderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jason on 2017/6/15.
 */
@RestController
@RequestMapping("/api/spider-task")
public class SpiderTaskController {
    @Autowired
    private SpiderTaskService spiderTaskService;

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String spiderTemplateId) {
        return null;
    }
}
