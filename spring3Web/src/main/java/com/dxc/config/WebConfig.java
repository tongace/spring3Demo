package com.dxc.config;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"com.dxc.application.controllers"})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public WebConfig() {
		super();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/resources/font/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/resources/scripts/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/templates/**").addResourceLocations("/WEB-INF/resources/templates/");
		registry.addResourceHandler("/pages/**").addResourceLocations("/WEB-INF/resources/pages/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localResolver = new CookieLocaleResolver();
		localResolver.setDefaultLocale(new Locale("en_US"));
		return localResolver;
	}
	/* **************************************************************** */
	/* THYMELEAF-SPECIFIC ARTIFACTS */
	/* TemplateResolver <- TemplateEngine <- ViewResolver */
	/* **************************************************************** */

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		// SpringResourceTemplateResolver automatically integrates with Spring's
		// own
		// resource resolution infrastructure, which is highly recommended.
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/resources/");
		templateResolver.setCharacterEncoding("UTF-8");
		// templateResolver.setSuffix(".html");
		// HTML is the default value, added here for the sake of clarity.
		// templateResolver.setTemplateMode(TemplateMode.HTML);
		// Template cache is true by default. Set to false if you want
		// templates to be automatically updated when modified.
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		// SpringTemplateEngine automatically applies SpringStandardDialect and
		// enables Spring's own MessageSource message resolution mechanisms.
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}
}