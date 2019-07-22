package com.example;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.example.util.ApplicationConfFactory;

@Named
@ViewScoped
public class GroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Group> groups;
	private Group selectedGroup;

	@Inject
	private FunctionsBean functions;

	@PostConstruct
	public void afterCreate() {
		log("GroupsBean afterCreate() ... ... ...");
		reload();
	}

	public void reload() {
		log("GroupsBean, load ... ... ...");
		groups = DataLoader.getDataLoader().loadGroups();
	}

	public void onRowSelect(SelectEvent event) {
		Group group = (Group) event.getObject();
		log("Selected Group (event) " + group.getName());
		log("Selected Group (this)  " + (this.selectedGroup != null ? this.selectedGroup.getName() : "?"));
		functions.setSelectedFunction(null);
		functions.reload();
	}

	public List<Group> getGroups() {
		log("GroupsBean ... getGroups");
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
		log("GroupBean setSelectedGroup " + selectedGroup.getName());
	}

	public String getDatabaseDetails() {
		return ApplicationConfFactory.getInstance().getDatabaseDetails();
	}

	private static void log(String message) {
		System.err.println(message);
	}
}
