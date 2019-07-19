package com.example.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class ApplicationConfPostgresqlUCP implements ApplicationConf {

    private PoolDataSource pds;

    @Override
    public void initialize() throws SQLException {
    	try {
    		pds = PoolDataSourceFactory.getPoolDataSource();
    		pds.setConnectionFactoryClassName(org.postgresql.jdbc3.Jdbc3SimpleDataSource.class.getName());

    	    URI dbUri = new URI(System.getenv("DATABASE_URL"));
    	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
    	    System.err.println("DB URL ... ... ... ... ... ... ... ... ... ... ... ... ... ... " + dbUrl + ", PATH=" + dbUri.getPath());
    	    if (dbUri.getUserInfo() != null) {
    	    	String user = dbUri.getUserInfo().split(":")[0];
    	    	String password = dbUri.getUserInfo().split(":")[1];
    	    	String dbName = dbUri.getPath().replace("/", "");

    	    	pds.setUser(user);
    		    pds.setPassword(password);
    		    pds.setDatabaseName(dbName); // otherwise it doesn't work;

    		    pds.setInitialPoolSize(2);
    		    pds.setMaxPoolSize(20);
    	    }
            pds.setURL(dbUrl);
        }
        catch (URISyntaxException ex) {
          ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
    	return pds.getConnection();
    }

}
