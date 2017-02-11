package com.epam.jmp2017.model.impl.data;

import com.epam.jmp2017.model.Data;


public class Dog implements Data {
    private String name;
    private String color;

    public Dog(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getTypeCode() {
        return 1;
    }
    @Override
    public String print() {
        return "Color of the dog called " + name + " is " + color + ".";
    }
}
