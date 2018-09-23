/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author TapojitBhattacharya
 *
 */
@Configuration
@EnableSwagger2 // Swagger 2 is enabled through the @EnableSwagger2 annotation.
public class SwaggerConfig {

	/**
	 * After the Docket bean is defined, its select() method returns an instance of
	 * ApiSelectorBuilder, which provides a way to control the endpoints exposed by
	 * Swagger.
	 */
	// http://localhost:8080/v2/api-docs
	// http://localhost:8080/swagger-ui.html

	/**
	 * The select() method called on the Docket bean instance returns an
	 * ApiSelectorBuilder, which provides the apis() and paths() methods that are
	 * used to filter the controllers and methods that are being documented using
	 * String predicates.
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
}
