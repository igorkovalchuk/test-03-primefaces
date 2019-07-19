package com.example.util;

public class ApplicationConfFactory {

	private static ApplicationConf conf;

	public static ApplicationConf getInstance() {

		if (conf == null) {
			String url = System.getenv("DATABASE_URL");

			if (url.startsWith("oracle")) {

				try {
					conf = (ApplicationConf) Class.forName("com.example.util.ApplicationConfOracle").newInstance();
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}

			} else if (url.startsWith("postgres")) {

				try {
					conf = (ApplicationConf) Class.forName("com.example.util.ApplicationConfPostgresqlUCP").newInstance();
				} catch (Exception ex) {
					conf = new ApplicationConfPostgresql();
				}

			} else {
				throw new RuntimeException("Unknown Database URL");
			}
			System.err.println("Using ... ... ... ... ... " + conf.getClass().getSimpleName());
		}

		return conf;
	}
}
