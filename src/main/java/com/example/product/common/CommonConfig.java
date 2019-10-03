package com.example.product.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@Configuration
@EnableSwagger2
public class CommonConfig {

	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                  
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))  
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
				.paths(PathSelectors.any())                          
				.build().apiInfo(metaData());                                           
	}
	
	private ApiInfo metaData() {
        
    	return new ApiInfoBuilder().title("Product Service Api")
        					.description("Product Service API")
        					.version("1.0")
        					.contact(new Contact("Ashish Sonawane", "https://github.com/staticashish",
        								"ashish280386@gmail.com"))
        					.build();
    }
	
	@Bean
    public CorsFilter corsFilter() {
           final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
           final CorsConfiguration config = new CorsConfiguration();
           config.setAllowCredentials(true);
           config.addAllowedOrigin("*");
           config.addAllowedHeader("*");
           config.addAllowedMethod("OPTIONS");
           config.addAllowedMethod("HEAD");
           config.addAllowedMethod("GET");
           config.addAllowedMethod("PUT");
           config.addAllowedMethod("POST");
           config.addAllowedMethod("DELETE");
           config.addAllowedMethod("PATCH");
           source.registerCorsConfiguration("/**", config);
           return new CorsFilter(source);
    }
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();        
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));       
		messageConverters.add(converter);
		return 	builder.messageConverters(messageConverters)
						.build();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper =  new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
