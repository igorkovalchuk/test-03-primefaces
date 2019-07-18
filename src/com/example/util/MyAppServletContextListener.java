package com.example.util;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("********************************************** ServletContextListener started");
		ApplicationConf.initialize();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("[**********************************************] ServletContextListener destroyed");
	}

}