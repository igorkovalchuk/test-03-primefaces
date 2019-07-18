package com.example;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class GroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Group> groups;
	private Group selectedGroup;

	@PostConstruct
	public void afterCreate() {
		log("GroupsBean afterCreate() ... ... ...");
		reload();
	}

	private void reload() {
		groups = DataLoader.getDataLoader().loadGroups();
	}

	public void onRowSelect(SelectEvent event) {
		log("Selected Group (event) " + ((Group) event.getObject()).getName());
		log("Selected Group (this)  " + (this.selectedGroup != null ? this.selectedGroup.getName() : "?"));
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		/*
		 * TO DO: we can reload it periodically here if any changes in the database,
		 * lets say once per 5 minutes; if ((System.currentTimeMillis() - previous) > 5
		 * * 60 * 1000) { previous = System.currentTimeMillis(); reload(); }
		 */
		this.groups = groups;
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	private static void log(String message) {
		System.err.println(message);
	}
}
