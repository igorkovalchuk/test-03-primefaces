package com.example.util;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("********************************************** ServletContextListener started");
		try {
			ApplicationConfFactory.getInstance().initialize();
		}
		catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("[**********************************************] ServletContextListener destroyed");
	}

}