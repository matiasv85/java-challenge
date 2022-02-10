package com.example.restservice.metrics.business;

public enum LoanType {
    CONSUMER("consumer"),
    STUDENT("student");

    public String name;

    LoanType(String name){
        this.name = name;
    }
}
