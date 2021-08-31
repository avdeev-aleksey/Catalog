package com.test.catalog.model;

public class Author {

    private String fio;
    private Integer year;
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public Integer getYear() {
        return year;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
