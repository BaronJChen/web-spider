package com.baron.program;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Created by Baron.Chen on 2017/6/3.
 */
@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(80);
        configurableEmbeddedServletContainer.setContextPath("/api");
    }
}
