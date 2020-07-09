package com.example.a200709_ex;

import java.io.Serializable;

public class CalculatedNum implements Serializable {

    private int num1;
    private int num2;
    private String operator;
    private int result;

    public CalculatedNum(int num1, int num2, String operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    public CalculatedNum(int num1, int num2, String operator, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        this.result = result;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
