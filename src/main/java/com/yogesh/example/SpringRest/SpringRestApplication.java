package com.yogesh.example.SpringRest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@SpringBootApplication
@EnableCaching
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	
	@Bean
	public SimpleFilterProvider simpleFilterProvider(){
		return new SimpleFilterProvider();
	}



	//This method will set the default locale
	@Bean
	public LocaleResolver localResolver() {
		SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
		sessionResolver.setDefaultLocale(Locale.ENGLISH);
		return sessionResolver;
	}
	

	@Bean
	//By default method name will be the bean name
	//This will read the messages.properties file
	public MessageSource messageSources() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}
}
