package com.example.util;

public class ApplicationConfFactory {

	private static ApplicationConf conf;

	public static ApplicationConf getInstance() {
		if (conf == null) {
			//conf = new ApplicationConfPostgresqlUCP();
			conf = new ApplicationConfPostgresql();
			System.err.println("Using ... ... ... ... ... " + conf.getClass().getSimpleName());
		}
		return conf;
	}
}
