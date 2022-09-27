package com.midominio.spring.configuracion;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.midominio.spring.upload.storage.StorageProperties;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class MyConfig {
	
	//Carga un recurso (un fichero) como bundle
	@Bean
	public ReloadableResourceBundleMessageSource messageResource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	//Usa el recurso cargado anteriormente. Lo siguiente es crear el fichero propierties para crear el fichero de errores
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageResource());
		return bean;
	}
}
