package com.example;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named
@RequestScoped
public class ParametersBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Parameter> parameters;
    private Parameter selectedParameter;
    private Parameter[] selectedParameters;

    @Inject
    private FunctionsBean functions;

    public void onRowSelect(SelectEvent event) {
        log("Parameters, Selected Function: " + ((Function) event.getObject()).getName());
        reload();
    }

    @PostConstruct
    public void afterCreate() {
        log("Parameters created ... ... ...");
    }

    private void reload() {
        if (functions != null && functions.getSelectedFunction() != null) {
            int functionId = functions.getSelectedFunction().getId();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            parameters = new ArrayList<>();
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

    public Parameter[] getSelectedParameters() {
        return selectedParameters;
    }

    public void setSelectedParameters(Parameter[] selectedParameters) {
        this.selectedParameters = selectedParameters;
    }

    private static void log(String message) {
        System.err.println(message);
    }

}
