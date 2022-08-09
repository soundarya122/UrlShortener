package com.urlApps.urlShortener.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author soundarya
 *
 * Added this to load the static html file as the home page
 *
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	@Override
	   public void addViewControllers(ViewControllerRegistry registry) {
	       registry.addViewController("/").setViewName("forward:/index.html");
	   }
}
	