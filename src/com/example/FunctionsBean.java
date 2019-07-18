package com.example;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named
@RequestScoped
public class FunctionsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Function> functions;
	private Function selectedFunction;

	@Inject
	private GroupsBean groups;

	public void onRowSelect(SelectEvent event) {
		log("FunctionsBean.onRowSelect(), Selected Group: " + ((Group) event.getObject()).getName());
		reload();
	}

	@PostConstruct
	public void afterCreate() {
		log("FunctionsBean.afterCreate() ... ... ...");
		reload();
	}

	private void reload() {
		if (groups != null && groups.getSelectedGroup() != null) {
			Group group = groups.getSelectedGroup();
			log("FunctionsBean, load ... ... ... group = " + group.getName());

			functions = DataLoader.getDataLoader().loadFunctions(group);
		}
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
	}

	private static void log(String message) {
		System.err.println(message);
	}

}
