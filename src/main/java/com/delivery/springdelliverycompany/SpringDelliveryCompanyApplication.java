package com.delivery.springdelliverycompany;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;


@SpringBootApplication
public class SpringDelliveryCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDelliveryCompanyApplication.class, args);
	}

/*	@Value("${messages.properties}")
	private String messagesBasename;

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setFallbackToSystemLocale(false);
		messageSource.setBasenames("file:" + messagesBasename);
		return messageSource;
	}*/



}
