package com.wb.config;

import com.wb.utility.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket authAPI(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(ApplicationConstants.GROUP_WB)
				.select()
				.apis(RequestHandlerSelectors.basePackage(ApplicationConstants.WB_API_BASEPACKAGR)).paths(PathSelectors.any())
				.build();
	}
	
	
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Mirosław Buszek", "", "miroslawbuszek@gmail.com");
		return new ApiInfoBuilder().title("Weather Forecast API").contact(contact).version("1.0").build();
	}


}
