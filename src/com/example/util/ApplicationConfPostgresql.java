package com.example.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ApplicationConfPostgresql implements ApplicationConf {

    private BasicDataSource connectionPool;

    @Override
    public void initialize() {

    	try {
    	    //String herokuDatabaseUrl = "postgres://string:string@string.site.com:port/string";
    	    URI dbUri = new URI(System.getenv("DATABASE_URL"));
    	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
    	    //String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
    	    connectionPool = new BasicDataSource();
    	    if (dbUri.getUserInfo() != null) {
    	    	String user = dbUri.getUserInfo().split(":")[0];
    	    	String password = dbUri.getUserInfo().split(":")[1];
    		    connectionPool.setUsername(user);
    		    connectionPool.setPassword(password);
    	    }
    	    connectionPool.setDriverClassName(org.postgresql.Driver.class.getName());
    	    //connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
            connectionPool.setUrl(dbUrl);

    	    connectionPool.setInitialSize(2);
    	    connectionPool.setMaxTotal(20);
        }
        catch (URISyntaxException ex) {
          ex.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
    	return connectionPool.getConnection();
    }

    @Override
	public String getDatabaseDetails() {
		return "DBCP2, PostgreSQL, " + connectionPool.getClass().getSimpleName() + ", " + connectionPool.getDriverClassName();
	}
}
