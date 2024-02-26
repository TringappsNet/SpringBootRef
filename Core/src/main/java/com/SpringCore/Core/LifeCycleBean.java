package com.SpringCore.Core;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class LifeCycleBean implements InitializingBean, DisposableBean {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Initializing LifeCycleBean: " + name);
    }

    @Override
    public void destroy() {
        System.out.println("Disposing LifeCycleBean: " + name);
    }
}