package com_springcore_core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MessageProvider messageProvider() {
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new MessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }

    @Bean
    public LifeCycleBean lifeCycleBean() {
        return new LifeCycleBean();
    }
}