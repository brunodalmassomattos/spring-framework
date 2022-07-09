package br.com.primeirojog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertiesConfig {
	
	@Bean
	public PropertySourcesPlaceholderConfigurer config() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new FileSystemResource("/Temp/batch/primeirojog/application.properties"));
		return configurer;
	}
}