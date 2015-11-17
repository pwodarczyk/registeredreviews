
package com.registeredreviews.util.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;


/**
 * 
 * 
 * This uses a static class to manage the context.
 * 
 * It depends on having the following bean defined in your applicationContext.xml:

 * 
 */
public class BeanLocator {

	public static ApplicationContext getApplicationContext() {
		return ApplicationContextProvider.getApplicationContext();
	}
	
	public static Object getBean( String name ) {
		return getApplicationContext().getBean( name );
	}
	
}
