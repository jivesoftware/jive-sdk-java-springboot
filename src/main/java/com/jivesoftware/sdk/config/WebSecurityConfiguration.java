package com.jivesoftware.sdk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
	    http
        .authorizeRequests()
        	.antMatchers("/**").permitAll()
        	.anyRequest().authenticated()
        .and()
        	.csrf().disable();
	} // end configure

} // end class
