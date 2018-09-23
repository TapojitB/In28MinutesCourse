package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	/**
	 * In order for our application to be able to determine which locale is
	 * currently being used, we need to add a LocaleResolver bean.
	 * 
	 * The LocaleResolver interface has implementations that determine the current
	 * locale based on the session, cookies, the Accept-Language header, or a fixed
	 * value.
	 * 
	 * In our example, we have used the session based resolver SessionLocaleResolver
	 * and set a default locale with value US.
	 **/
	/*
	 * @Bean public LocaleResolver localeResolver() { SessionLocaleResolver slr =
	 * new SessionLocaleResolver(); slr.setDefaultLocale(Locale.US); return slr; }
	 */

	/**
	 * The following is the LocaleResolver implementation that simply uses the primary locale specified
	 * in the "accept-language" header of the HTTP request (that is, the locale sent
	 * by the client browser, normally that of the client's OS).
	 * 
	 * If we use AcceptHeaderLocaleResolver, then we no longer need to configure
	 * Locale as a request parameter or request header in each and every controller
	 * method. We can use LocaleContextHolder.getLocale() and pick the accept-header
	 * locale.
	 **/
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	/*
	 * @Bean public MessageSource messageSource() {
	 * ReloadableResourceBundleMessageSource messageSource = new
	 * ReloadableResourceBundleMessageSource();
	 * messageSource.setBasename("classpath:messages");
	 * messageSource.setCacheSeconds(10); // reload messages every 10 seconds return
	 * messageSource; }
	 */
}
