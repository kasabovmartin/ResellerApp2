package com.resellerapp.model.entity;

public enum ConditionName {

    EXCELLENT("Excellent"),
    GOOD("Good"),
    ACCEPTABLE("Acceptable");

    private String value;

    ConditionName(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
