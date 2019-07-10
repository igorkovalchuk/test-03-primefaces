package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
@Named
@RequestScoped
public class FunctionsBean implements Serializable {
            
    private static final long serialVersionUID = 1L;

    //private Log log = LogFactory.getLog(CarDetails.class);
    private List<Function> functions;
    private Function selectedFunction;
    private Function[] selectedFunctions;

    @Inject
    private GroupsBean groups;

    public void onRowSelect(SelectEvent event) {
        log("Functions, Selected Group: " + ((Group) event.getObject()).getName());
        reload();
    }

    @PostConstruct
    public void afterCreate() {
        log("Functions created ... ... ...");
        reload();
    }

    private void reload() {
        if (groups != null && groups.getSelectedGroup() != null) {
            String groupName = groups.getSelectedGroup().getName();

            log("Functions, load ... ... ... group = " + groupName);

            functions = new ArrayList<>();
            if (groupName.endsWith("A")) {
                functions.add(new Function(1, "cos(a)", "", new Date()));
                functions.add(new Function(2, "sin(a)", "", new Date()));
            } else if (groupName.endsWith("B")) {
                functions.add(new Function(3, "pow(x, y) = x^y", "", new Date()));
                functions.add(new Function(4, "f(x, y) = x/y", "", new Date()));
            } else {
            }
        }
    }

    public List<Function> getFunctions() {
        log("getFunctions ...");
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public Function getSelectedFunction() {
        return selectedFunction;
    }

    public void setSelectedFunction(Function selectedFunction) {
        this.selectedFunction = selectedFunction;
    }

    public Function[] getSelectedFunctions() {
        return selectedFunctions;
    }

    public void setSelectedFunctions(Function[] selectedFunctions) {
        this.selectedFunctions = selectedFunctions;
    }

    private static void log(String message) {
        System.err.println(message);
    }

}
