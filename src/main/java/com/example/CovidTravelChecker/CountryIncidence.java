package com.example.CovidTravelChecker;

public class CountryIncidence {
    private String name;
    private double incidence;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncidence() {
        return incidence;
    }

    public void setIncidence(double incidence) {
        this.incidence = incidence;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Data [name=" + name + ", incidence=" + incidence  + "]";
    }
}
