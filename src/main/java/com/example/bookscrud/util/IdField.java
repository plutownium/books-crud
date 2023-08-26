package com.example.bookscrud.util;

public class IdField {
    private Integer id;

    public IdField() {
        // no-arg default consturctor;
    }

    public IdField(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}