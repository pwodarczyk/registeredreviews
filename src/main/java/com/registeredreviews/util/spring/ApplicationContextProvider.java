
package com.registeredreviews.util.spring;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;


public class ApplicationContextProvider implements ApplicationContextAware, ServletContextAware  {

	private static ApplicationContext applicationContext = null;
	private static ServletContext servletContext = null;

	public static ApplicationContext getApplicationContext() {
		
		if (applicationContext == null) {
			throw new IllegalStateException("ApplicationContextProvider accessed without being initialized. You must include a reference to this bean in your Spring config file.");
		}
		
		return applicationContext;
	}

	public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public static ServletContext getServletContext() {
		
		if (servletContext == null) {
			throw new IllegalStateException("ApplicationContextProvider accessed without being initialized. You must include a reference to this bean in your Spring config file.");
		}
		
		return servletContext;
	}

	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
}