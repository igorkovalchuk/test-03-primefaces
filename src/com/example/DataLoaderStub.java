package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLoaderStub implements DataLoader {

	public List<Group> loadGroups() {
		List<Group> groups = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            groups.add(new Group(1, "Group A", "", sdf.parse("2000-01-01")));
            groups.add(new Group(15, "Group B", "", sdf.parse("1996-01-01")));
            groups.add(new Group(20, "Group C", "", sdf.parse("2003-01-01")));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    	return groups;
	}

	public List<Function> loadFunctions(Group group) {
		String groupName = group.getName();

		List<Function> functions = new ArrayList<>();

        if (groupName.endsWith("A")) {
            functions.add(new Function(1, "cos(a)", "", new Date()));
            functions.add(new Function(2, "sin(a)", "", new Date()));
        } else if (groupName.endsWith("B")) {
            functions.add(new Function(3, "pow(x, y) = x^y", "", new Date()));
            functions.add(new Function(4, "f(x, y) = x/y", "", new Date()));
        } else {
        }

    	return functions;
	}

	public List<Parameter> loadParameters(Function function) {
		int functionId = function.getId();
		List<Parameter> parameters = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (functionId == 1) {
                parameters.add(new Parameter(1, "a", "", sdf.parse("2000-01-01")));
            } else if (functionId == 2) {
                parameters.add(new Parameter(4, "a", "", sdf.parse("1996-01-01")));
            } else if (functionId == 3) {
                parameters.add(new Parameter(9, "x", "", sdf.parse("2003-01-01")));
                parameters.add(new Parameter(10, "y", "", sdf.parse("2003-01-02")));
            }
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    	return parameters;
	}

}
