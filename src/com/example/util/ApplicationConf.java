package com.example.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ApplicationConf {

    private static BasicDataSource connectionPool;

    static void initialize() {

    	try {
    	    //String herokuDatabaseUrl = "postgres://string:string@string.site.com:port/string";
    	    URI dbUri = new URI(System.getenv("DATABASE_URL"));
    	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
    	    //String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
    	    //System.err.println("URL ........................................... " + dbUrl);
    	    connectionPool = new BasicDataSource();
    	    if (dbUri.getUserInfo() != null) {
    		    connectionPool.setUsername(dbUri.getUserInfo().split(":")[0]);
    		    connectionPool.setPassword(dbUri.getUserInfo().split(":")[1]);
    	    }
    	    connectionPool.setDriverClassName("org.postgresql.Driver");
    	    //connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
            connectionPool.setUrl(dbUrl);
    	    connectionPool.setInitialSize(1);
        }
        catch (URISyntaxException ex) {
          ex.printStackTrace();
        }

    }

    public static BasicDataSource getConnectionPool() {
    	return connectionPool;
    }

}
