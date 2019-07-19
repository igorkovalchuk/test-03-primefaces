package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.util.ApplicationConfFactory;

public class DataLoaderImpl implements DataLoader {

	public List<Group> loadGroups() {
		List<Group> groups = new ArrayList<>();

    	try (Connection connection = ApplicationConfFactory.getInstance().getConnection();
    			Statement stmt = connection.createStatement();
    			ResultSet rs = stmt.executeQuery("SELECT * FROM \"GROUP_FUNCTION\""))
    	{
    		while (rs.next()) {
    			groups.add(new Group(rs.getInt("ID"),
    					rs.getString("NAME"),
    					rs.getString("DESCR"),
    					rs.getDate("CTIME")));
    		}
    	}
    	catch (SQLException ex) {
    		ex.printStackTrace();
    	}

    	return groups;
	}

	public List<Function> loadFunctions(Group group) {
		List<Function> functions = new ArrayList<>();

		if (group == null)
			return functions;

    	try (Connection connection = ApplicationConfFactory.getInstance().getConnection();
    			Statement stmt = connection.createStatement();
    			ResultSet rs = stmt.executeQuery("SELECT * FROM \"FUNCTIONS\" WHERE \"ID_GROUP\" = " + group.getId()))
    	{
    		while (rs.next()) {
    			functions.add(new Function(rs.getInt("ID"),
    					rs.getString("NAME"),
    					rs.getString("DESCR"),
    					rs.getDate("CTIME")));
    		}
    	}
    	catch (SQLException ex) {
    		ex.printStackTrace();
    	}

    	return functions;
	}

	public List<Parameter> loadParameters(Function function) {
		List<Parameter> parameters = new ArrayList<>();

        if (function == null)
        	return parameters;

    	try (Connection connection = ApplicationConfFactory.getInstance().getConnection();
    			Statement stmt = connection.createStatement();
    			ResultSet rs = stmt.executeQuery("SELECT * FROM \"FUN_PARAM\" WHERE \"ID_FUN\" = " + function.getId()))
    	{
    		while (rs.next()) {
    			parameters.add(new Parameter(rs.getInt("ID"),
    					rs.getString("NAME"),
    					rs.getString("DESCR"),
    					rs.getDate("CTIME")));
    		}
    	}
    	catch (SQLException ex) {
    		ex.printStackTrace();
    	}

    	return parameters;
	}

}
