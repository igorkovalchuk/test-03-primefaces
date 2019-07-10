package com.example;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.logging.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
@Named
@SessionScoped
public class GroupsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Group> groups;
    private Group selectedGroup;
    private Group[] selectedGroups;

    @PostConstruct
    public void afterCreate() {
        log("Groups created ... ... ...");
        reload();
    }

    private void reload() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        groups = new ArrayList<>();
        try {
            groups.add(new Group(1, "Group A", "", sdf.parse("2000-01-01")));
            groups.add(new Group(15, "Group B", "", sdf.parse("1996-01-01")));
            groups.add(new Group(20, "Group C", "", sdf.parse("2003-01-01")));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void onRowSelect(SelectEvent event) {
        log("Selected Group " + ((Group) event.getObject()).getName());
        log("Selected_ Group " + (this.selectedGroup != null ? this.selectedGroup.getName() : "?"));
    }

    public List<Group> getGroups() {
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
    }

    public Group[] getSelectedGroups() {
        return selectedGroups;
    }

    public void setSelectedGroups(Group[] selectedGroups) {
        this.selectedGroups = selectedGroups;
    }

    private static void log(String message) {
        System.err.println(message);
    }
}
