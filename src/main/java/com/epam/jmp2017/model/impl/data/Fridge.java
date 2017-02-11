package com.epam.jmp2017.model.impl.data;

import com.epam.jmp2017.model.Data;

public class Fridge implements Data {
    private int weight;
    private String brand;

    public Fridge(int weight, String brand) {
        this.weight = weight;
        this.brand = brand;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int getTypeCode() {
        return 2;
    }
    public String print() {
        return "This fridge from " + brand + " weight " + weight + " kg.";
    }
}
