package com_springcore_core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Main application entry point
@SpringBootApplication // (Potentially unused in this context)
public class CoreApplication {

	public static void main(String[] args) {
		// Create Spring application context using XML configuration
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Retrieve beans from the context
		LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
		lifeCycleBean.setName("lifecycle-bean");

		MessageRenderer renderer = context.getBean(MessageRenderer.class);
		renderer.render();

		// (Optional) Get the application name (useful for multi-context environments)
		context.getApplicationName();
	}
}
