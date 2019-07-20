package com.example;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {

    private static final long serialVersionUID = 2L;

    private int id;
    private String name;
    private String descr;
    private Date ctime;

    private int inactive;
    private int all;

    public Group() {
    }

    public Group(int id, String model, String descr, Date ctime, int inactive, int all) {
        this.id = id;
        this.name = model;
        this.descr = descr;
        this.ctime = ctime;
        this.inactive = inactive;
        this.all = all;
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

	public int getInactive() {
		return inactive;
	}

	public void setInactive(int inactive) {
		this.inactive = inactive;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getActive() {
		return this.all - this.inactive;
	}
}
