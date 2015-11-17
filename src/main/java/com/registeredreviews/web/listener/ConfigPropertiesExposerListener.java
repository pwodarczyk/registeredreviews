package com.registeredreviews.web.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.registeredreviews.util.PropertyConfigUtil;

public class ConfigPropertiesExposerListener implements ServletContextListener {
	  public static final String DEFAULT_PROPERTIES_BEAN_NAME = "propertyConfigurer";

	  public static final String DEFAULT_CONTEXT_PROPERTY = "configProperties";

	  private String propertiesBeanName = DEFAULT_PROPERTIES_BEAN_NAME;

	  private String contextProperty = DEFAULT_CONTEXT_PROPERTY;

	  public void contextDestroyed(ServletContextEvent sce) {
	  }
	  

	  public void contextInitialized(ServletContextEvent sce) {

	      ServletContext servletContext = sce.getServletContext();
	      WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	      PropertyConfigUtil configurer = (PropertyConfigUtil) context.getBean(propertiesBeanName);
	      Map<String, String> vals = configurer.getResolvedProps();
//	      for(String key : vals.keySet()) {
//	    	  System.out.println(key + "->"+vals.get(key));
//	      }
	      sce.getServletContext().setAttribute(contextProperty, configurer.getResolvedProps());

	  }
}
