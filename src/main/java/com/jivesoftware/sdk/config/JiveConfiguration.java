package com.jivesoftware.sdk.config;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jivesoftware.sdk.dao.JiveInstanceRepository;
import com.jivesoftware.sdk.services.filters.JiveSignedFetchValidationFilter;

@Configuration
@EnableJpaRepositories({ "com.jivesoftware.sdk" })
@ComponentScan({ "com.jivesoftware.sdk" })
@EnableTransactionManagement
public class JiveConfiguration {
	
	@Autowired
	private JiveInstanceRepository jiveInstanceDAO;
	
	@Bean
	public FilterRegistrationBean jiveSignedFetchValidationFilter() { 
		FilterRegistrationBean registration = new FilterRegistrationBean(new JiveSignedFetchValidationFilter(jiveInstanceDAO));
		registration.setOrder(1);
		registration.setEnabled(true);
		/***** PATHS DEFAULT ******/
		registration.addUrlPatterns(
				"/jive/tile/register",
				"/jive/tile/unregister",
				"/jive/webhook",
				/*** SEE: ExampleSignedFetchController ***/
				"/api/example/*");
		return registration;
	} // end jiveSignedFetchValidationFilter
	
	
	@ExceptionHandler
	void handleBadRequests(BadRequestException bre, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value(),bre.getMessage());
	} // end handleBadRequests
	
	@ExceptionHandler
	void handleUnauthorizedRequests(NotAuthorizedException nae, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.UNAUTHORIZED.value(),nae.getMessage());
	} // end handleUnauthorizedRequests
	
} // end class