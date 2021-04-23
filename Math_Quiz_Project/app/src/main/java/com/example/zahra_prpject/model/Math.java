package com.example.zahra_prpject.model;

import java.io.Serializable;

public class Math implements Comparable , Serializable {

    private String operation;
    private String validation;
    private double answer;

    public Math(String operation, String validation, double answer) {
        this.operation = operation;
        this.validation = validation;
        this.answer = answer;
    }

    public String getOperation() {
        return operation;
    }

    public String getValidation() {
        return validation;
    }

    public double getResult() {
        return answer;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public void setResult(double result) {
        this.answer = result;
    }

    @Override
    public String toString() {
        return "'" +answer +"' for '"+  operation + "'  is a "+ validation+ " answer";
    }

    @Override
    public int compareTo(Object obj) {
        Math otherObject = (Math) obj;
        return validation.compareTo(otherObject.getValidation());
    }
}