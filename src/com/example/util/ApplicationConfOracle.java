package com.example.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class ApplicationConfOracle implements ApplicationConf {

	private PoolDataSource pds;

	@Override
	public void initialize() throws SQLException {
		try {
			pds = PoolDataSourceFactory.getPoolDataSource();
			pds.setConnectionFactoryClassName(oracle.jdbc.pool.OracleDataSource.class.getName());

			String strUrl = System.getenv("DATABASE_URL");
			String url = strUrl.replace("#", "%23");

			URI dbUri = new URI(url);

			String port = "";
			if (dbUri.getPort() != -1)
				port = ":" + String.valueOf(dbUri.getPort());

			String dbUrl = "jdbc:oracle:thin:@//" + dbUri.getHost() + port;
			System.err.println("DB URL ... ... ... ... ... ... ... ... ... ... ... ... ... ... " + dbUrl);
			if (dbUri.getUserInfo() != null) {
				String user = dbUri.getUserInfo().split(":")[0];
				String password = dbUri.getUserInfo().split(":")[1];

				pds.setUser(user);
				pds.setPassword(password);

				pds.setInitialPoolSize(1);
				pds.setMaxPoolSize(20);
			}
			pds.setURL(dbUrl);
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return pds.getConnection();
	}

	@Override
	public String getDatabaseDetails() {
		return "UCP, Oracle, " + pds.getClass().getSimpleName() + ", " + pds.getConnectionFactoryClassName();
	}

}
