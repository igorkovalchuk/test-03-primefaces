package com.example;

import java.util.List;

public interface DataLoader {

	public static DataLoader getDataLoader() {
		return new DataLoaderImpl();
	}

	public List<Group> loadGroups();

	public List<Function> loadFunctions(Group group);

	public List<Parameter> loadParameters(Function function);
}