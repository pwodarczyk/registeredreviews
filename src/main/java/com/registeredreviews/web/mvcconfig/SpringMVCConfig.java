package com.registeredreviews.web.mvcconfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class SpringMVCConfig extends WebMvcConfigurationSupport {
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		
		RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();		
		handlerMapping.setUseTrailingSlashMatch(false);
		return handlerMapping;
	}
	
	@Bean
    public FormattingConversionService mvcConversionService() {
		FormattingConversionServiceFactoryBean serviceFactoryBean = new FormattingConversionServiceFactoryBean();
	    Set<Object> converters = new HashSet<Object>();
	    converters.add(new CustomStringToArrayConverter());
	    serviceFactoryBean.setConverters(converters);
	    serviceFactoryBean.afterPropertiesSet();
	    
	    FormattingConversionService conversionService = serviceFactoryBean.getObject();
        return conversionService;
    }
}