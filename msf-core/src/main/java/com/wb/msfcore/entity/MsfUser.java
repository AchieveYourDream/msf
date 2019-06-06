package com.wb.msfcore.entity;

import java.io.Serializable;

public class MsfUser implements Serializable {
    private Integer id;

    private String name;

    private String dataSourec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataSourec() {
        return dataSourec;
    }

    public void setDataSourec(String dataSourec) {
        this.dataSourec = dataSourec;
    }
}