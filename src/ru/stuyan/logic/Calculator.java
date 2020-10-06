package ru.stuyan.logic;

public class Calculator {

    private Integer a;

    private Integer b;

    private String math;

    public Calculator() {

    }

    public Calculator(Integer a, Integer b, String math) {
        this.a = a;
        this.b = b;
        this.math = math;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }
}
