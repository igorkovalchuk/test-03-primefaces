package com.example;

import java.io.Serializable;
import java.util.Date;

public class Function implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String descr;
    private Date ctime;

    public Function(int id, String name, String descr, Date ctime) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.ctime = ctime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}
