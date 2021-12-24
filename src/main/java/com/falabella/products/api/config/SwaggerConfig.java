package com.falabella.products.api.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import springfox.documentation.service.Tag;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		Set<String> responseProduceType = new HashSet<String>();
		responseProduceType.add("application/json");
		responseProduceType.add("application/xml");
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.falabella.products.api.controller"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.genericModelSubstitutes(ResponseEntity.class)
				.produces(responseProduceType)
				.consumes(responseProduceType)				
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Store Products API",
				"Manage products store.",
				"1.0",
				"http://falabella.com/terms",
				new Contact("Tomas Gonzalez", "https://falabella.com", "api@falabella.com"),
				"LICENSE",
				"LICENSE URL", 
				Collections.emptyList()
				);
	}
}