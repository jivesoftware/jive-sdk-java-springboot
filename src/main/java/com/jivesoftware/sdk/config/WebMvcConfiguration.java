package com.jivesoftware.sdk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/admin").setViewName("admin-index");
//        registry.addViewController("/about").setViewName("about");
//        registry.addViewController("/denied").setViewName("denied");
//        registry.addViewController("/register").setViewName("register");
//        registry.addViewController("/activate").setViewName("activate");
//        registry.addViewController("/login").setViewName("login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    } // end addViewControllers
    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    	super.addResourceHandlers(registry);
//    	registry.addResourceHandler("**/favicon.ico").addResourceLocations("classpath://resources/static/favicon.ico");
//    } // end addResourceHandlers

} // end class