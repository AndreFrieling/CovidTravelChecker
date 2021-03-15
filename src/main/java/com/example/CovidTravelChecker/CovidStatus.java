package com.example.CovidTravelChecker;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CovidStatus {
    public String getStatus(String[] countries) throws IOException, InterruptedException {
        String country;
        JSONObject numbers = null;
        CovidNumberExtractor numberExtractor = new CovidNumberExtractor();
        List<String> incidenceByCountry = new ArrayList<String>();
        for(int index = 0; index < countries.length; index++) {
            country = countries[index];
            numbers = numberExtractor.getNumbers(country);
            incidenceByCountry.add(country + " : ");
        }
        //return incidenceByCountry.toString();
        return numbers.toString();
    }

    private long calculateIncidence(int now, int past, int people){
        int newInfected = now - past;
        return (newInfected/people)*100000;
    }

}
