package com.example.CovidTravelChecker;

public class RequestBody {
    private String[] countries;

    public RequestBody(){}

    public RequestBody(String[] countries){
        this.countries = countries;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }
}
