package com.baron.repository;

import com.baron.model.SpiderTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by Jason on 2017/6/13.
 */
@Component
public interface SpiderTemplateRepository extends MongoRepository<SpiderTemplate, String> {
    SpiderTemplate findById(String id);
}
