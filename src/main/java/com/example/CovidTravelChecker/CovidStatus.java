package com.example.CovidTravelChecker;

public class CovidStatus {
    public String getStatus(String[] countries){
        CovidNumberExtractor numberExtractor = new CovidNumberExtractor();
        int[] numbers = numberExtractor.getNumbers(countries);
        return "";
    }

}
