package com.example.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface ApplicationConf {

	void initialize() throws SQLException;

	Connection getConnection() throws SQLException;
}
