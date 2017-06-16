package com.baron.repository;

import com.baron.model.SpiderTask;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 2017/6/13.
 */
@Component
public interface SpiderTaskRepository extends Repository<SpiderTask, String> {
    SpiderTask findById(String id);
}
