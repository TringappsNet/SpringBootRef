package com_springcore_core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Main application entry point
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		// Create Spring application context using annotation configuration
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		// Retrieve beans from the context
		LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
		lifeCycleBean.setName("lifecycle-bean");

		MessageRenderer renderer = context.getBean(MessageRenderer.class);
		renderer.render();

		// (Optional) Get the application name (useful for multi-context environments)
		context.getApplicationName();
	}
}