package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class FunctionsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Function> functions;
	private Function selectedFunction;

	@Inject
	private GroupsBean groups;

	@Inject
	private ParametersBean parameters;

	public void onRowSelect(SelectEvent event) {
		Function function = (Function) event.getObject();
		log("FunctionsBean.onGroupSelect(), Selected Group: " + function.getName());
		parameters.reload();
	}

	@PostConstruct
	public void afterCreate() {
		log("FunctionsBean.afterCreate() ... ... ...");
		reload();
	}

	public void reload() {
		if (groups != null && groups.getSelectedGroup() != null) {
			Group group = groups.getSelectedGroup();
			log("FunctionsBean, load ... ... ... group = " + group.getName());

			functions = DataLoader.getDataLoader().loadFunctions(group);
		}
		else {
			log("FunctionsBean, load/reset ... ... ...");
			functions = new ArrayList<>();
		}
		parameters.reset();
	}

	public List<Function> getFunctions() {
		log("FunctionsBean.getFunctions() ...");
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
		log("FunctionsBean setSelectedFunction " + (selectedFunction == null ? "null" : selectedFunction.getName()));
	}

	private static void log(String message) {
		System.err.println(message);
	}

}
