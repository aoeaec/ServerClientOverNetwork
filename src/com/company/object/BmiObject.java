package com.company.object;

import java.io.Serializable;

public class BmiObject implements Serializable {

    private double weight;
    private double height;

    public BmiObject(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
