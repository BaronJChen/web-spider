package com.baron.util;

import com.baron.program.AppCache;
import com.baron.program.AppConstants;
import com.google.common.base.Preconditions;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/13.
 */
public class SpringUtil {
    private SpringUtil() {
    }

    public static ApplicationContext getApplicationContext() {
        ApplicationContext context = AppCache.get(AppConstants.CACHE_SPRING_APPLICATON_CONTEXT);
        Preconditions.checkNotNull(context, AppConstants.SPRING_APPLICATION_CONTEXT_NULL);
        return context;
    }

    public <T> T getBean(Class<T> clazz) {
        ApplicationContext context = getApplicationContext();
        return context.getBean(clazz);
    }

    public <T> T getBean(String name) {
        ApplicationContext context = getApplicationContext();
        return (T) context.getBean(name);
    }

    public <T> List<T> getBeans(Class<T> clazz) {
        ApplicationContext context = getApplicationContext();
        Map<String, T> beanMap = context.getBeansOfType(clazz);
        List<T> beans = new ArrayList<>();

        beans.forEach(bean -> beans.add(bean));
        return beans;
    }

    public List<Object> getBeans(List<String> names) {
        ApplicationContext context = getApplicationContext();
        List<Object> beans = new ArrayList<>();
        names.forEach(name -> beans.add(context.getBean(name)));
        return beans;
    }
}
