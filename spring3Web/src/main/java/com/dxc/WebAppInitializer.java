package com.dxc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.dxc.web.listener.SessionListener;

import th.co.toyota.sc2.client.offline.CSC22110OfflineFilter;
import th.co.toyota.sc2.client.web.filter.CSC2110CasSingleSignOutFilter;
import th.co.toyota.sc2.client.web.filter.CSC22110AuthenticationFilter;
import th.co.toyota.sc2.client.web.filter.CSC22110AuthorizationFilter;
import th.co.toyota.sc2.client.web.filter.CSC22110CasAuthenticationFilter;

public class WebAppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan("com.dxc.config");
		context.setDisplayName("demo");

		context.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new SessionListener());
		context.refresh();

		ConfigurableEnvironment registeredProterties = context.getEnvironment();
		String activeProfile = registeredProterties.getProperty("spring.profiles.active");
		// START: SC2 CAS
		if (StringUtils.equalsIgnoreCase("OFFLINE", activeProfile)) {
			servletContext.addFilter("SC2 Offline Filter", CSC22110OfflineFilter.class).addMappingForUrlPatterns(null, false, "/*");
		} else {
			String casServerUrlPrefix = registeredProterties.getProperty("casServerUrlPrefix");
			String casServerLoginUrl = registeredProterties.getProperty("casServerLoginUrl");
			String serverName = registeredProterties.getProperty("serverName");
			// set context param
			servletContext.setInitParameter("casServerUrlPrefix", casServerUrlPrefix);
			servletContext.setInitParameter("casServerLoginUrl", casServerLoginUrl);
			servletContext.setInitParameter("serverName", serverName);
			// set filter
			servletContext.addFilter("CAS Single Sign Out Filter", CSC2110CasSingleSignOutFilter.class).addMappingForUrlPatterns(null, false, "/*");
			servletContext.addFilter("SC2 Authentication Filter", CSC22110AuthenticationFilter.class).addMappingForUrlPatterns(null, false, "/*");
			servletContext.addFilter("SC2-CAS Authentication Filter", CSC22110CasAuthenticationFilter.class).addMappingForUrlPatterns(null, false, "/*");
			servletContext.addFilter("CAS Ticket Validation Filter", Cas20ProxyReceivingTicketValidationFilter.class).addMappingForUrlPatterns(null, false, "/*");
			servletContext.addFilter("CAS HttpServletRequest Wrapper Filter", HttpServletRequestWrapperFilter.class).addMappingForUrlPatterns(null, false, "/*");
			servletContext.addFilter("SC2 Authorization Filter", CSC22110AuthorizationFilter.class).addMappingForUrlPatterns(null, false, "/*");
			// listener
			servletContext.addListener(SingleSignOutHttpSessionListener.class);
		}
		// END: SC2 CAS

		// Spring MVC front controller
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
