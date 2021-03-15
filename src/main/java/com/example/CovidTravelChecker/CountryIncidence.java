package com.example.CovidTravelChecker;

public class CountryIncidence {
    private String name;
    private double incidence;

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

    @Override
    public String toString() {
        return "Data [name=" + name + ", incidence=" + incidence  + "]";
    }
}
