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

		//String sql = "SELECT * FROM \"GROUP_FUNCTION\"";
		
		// Works for PostgreSQL and Oracle;
		String sql = "SELECT gf.\"ID\", gf.\"NAME\", gf.\"DESCR\", gf.\"CTIME\"," + 
				" COUNT(f.\"ID\") as \"ALL\"," + 
				" COUNT(CASE f.\"IS_ACTIVE\" WHEN 'N' THEN 1 ELSE NULL END) as \"INACTIVE\"" + 
				" FROM \"GROUP_FUNCTION\" gf LEFT JOIN \"FUNCTIONS\" f ON gf.\"ID\" = f.\"ID_GROUP\"" + 
				" GROUP BY gf.\"ID\", gf.\"NAME\", gf.\"CTIME\"" + 
				" ORDER BY gf.\"ID\"";

    	try (Connection connection = ApplicationConfFactory.getInstance().getConnection();
    			Statement stmt = connection.createStatement();
    			ResultSet rs = stmt.executeQuery(sql))
    	{
    		while (rs.next()) {
    			groups.add(new Group(rs.getInt("ID"),
    					rs.getString("NAME"),
    					rs.getString("DESCR"),
    					rs.getDate("CTIME"),
    					rs.getInt("INACTIVE"),
    					rs.getInt("ALL")
    			));
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
    			ResultSet rs = stmt.executeQuery("SELECT * FROM \"FUNCTIONS\" WHERE \"ID_GROUP\" = " + group.getId() + " ORDER BY \"ID\""))
    	{
    		while (rs.next()) {
    			functions.add(new Function(rs.getInt("ID"),
    					rs.getString("NAME"),
    					rs.getString("DESCR"),
    					rs.getDate("CTIME"),
    					rs.getString("IS_ACTIVE")));
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
    			ResultSet rs = stmt.executeQuery("SELECT * FROM \"FUN_PARAM\" WHERE \"ID_FUN\" = " + function.getId() + " ORDER BY \"ID\""))
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
