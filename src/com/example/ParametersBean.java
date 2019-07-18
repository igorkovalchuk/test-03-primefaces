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
public class ParametersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Parameter> parameters;
	private Parameter selectedParameter;

	@Inject
	private FunctionsBean functions;

	public void onRowSelect(SelectEvent event) {
		log("ParametersBean.onRowSelect(), Selected Function: " + ((Function) event.getObject()).getName());
		reload();
	}

	@PostConstruct
	public void afterCreate() {
		log("ParametersBean.afterCreate() ... ... ...");
	}

	private void reload() {
		if (functions != null && functions.getSelectedFunction() != null) {
			Function function = functions.getSelectedFunction();
			log("ParametersBean, load ... ... ... function id = " + function.getId());

			parameters = DataLoader.getDataLoader().loadParameters(function);
		}
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
