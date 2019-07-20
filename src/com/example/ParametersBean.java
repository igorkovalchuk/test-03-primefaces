package com.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ParametersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Parameter> parameters;
	private Parameter selectedParameter;

	@Inject
	private FunctionsBean functions;

	/*
	public void onRowSelect(SelectEvent event) {
		log("ParametersBean.onRowSelect(), Selected : " + ((Parameter) event.getObject()).getName());
	}
	*/

	@PostConstruct
	public void afterCreate() {
		log("ParametersBean.afterCreate() ... ... ...");
	}

	public void reload() {
		if (functions != null && functions.getSelectedFunction() != null) {
			Function function = functions.getSelectedFunction();
			log("ParametersBean, load ... ... ... function id = " + function.getId());

			parameters = DataLoader.getDataLoader().loadParameters(function);
		}
		else {
			log("ParametersBean, load/reset ... ... ...");
			parameters = new ArrayList<>();
		}
	}

	public void reset() {
		log("ParametersBean, reset ... ... ...");
		parameters = new ArrayList<>();
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Parameter getSelectedParameter() {
		return selectedParameter;
	}

	public void setSelectedParameter(Parameter selectedParameter) {
		this.selectedParameter = selectedParameter;
	}

	private static void log(String message) {
		System.err.println(message);
	}

}
