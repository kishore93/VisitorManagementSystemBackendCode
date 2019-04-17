package com.infy.visitor.management.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter{
	private static final String ERROR = "Error";
	@Bean
	public Docket ariaIntegrationServiceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
        		.globalResponseMessage(RequestMethod.GET,responseMessages())
        		.globalResponseMessage(RequestMethod.POST,responseMessages())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.infy.visitor.management.controller.impl"))
				.build().apiInfo(metaData());
	}
	
	private List<ResponseMessage> responseMessages(){
    	List<ResponseMessage>  responseMessages=new ArrayList<>();
    	responseMessages.add(new ResponseMessageBuilder()   
			    .code(401)
			    .message("You are not authorized to view the resource")
			    .responseModel(new ModelRef(ERROR))
			    .build());
    	responseMessages.add(new ResponseMessageBuilder()   
			    .code(403)
			    .message("Accessing the resource you were trying to reach is forbidden")
			    .responseModel(new ModelRef(ERROR))
			    .build());
    	responseMessages.add(new ResponseMessageBuilder()   
			    .code(404)
			    .message("The resource you were trying to reach is not found")
			    .responseModel(new ModelRef(ERROR))
			    .build());
    	responseMessages.add(new ResponseMessageBuilder() 
			      .code(500)
			      .message("Exception")
			      .build());
    	return responseMessages;
    }

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Spring Boot REST API for Visitor management Service")
				.description("\"Visitor management Service is a microservice which integrates Visitor UI \"")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"").contact(new Contact("Neeraj Sanodiya",
						"https://payment-ui.apps.np.sdppcf.com/", "neeraj.sanodiya@infosys.com"))
				.build();
	}
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	          .addResourceLocations("classpath:/META-INF/resources/");
	     
	        registry.addResourceHandler("/webjars/**")
	          .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	    
	   @Bean
	    public InternalResourceViewResolver viewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("WEB-INF/pages/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	    }
}