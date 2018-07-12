package com.dxc.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener {
	final Logger logger = LoggerFactory.getLogger(SessionListener.class);
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		logger.info("==== Session is created ====");
		// session timeout 30 minute
		event.getSession().setMaxInactiveInterval(30 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		logger.info("==== Session is destroyed ====");
	}

}
