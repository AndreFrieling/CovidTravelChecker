package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CovidStatus {
    public String getStatus(String[] countries) throws IOException, InterruptedException, JSONException {
        String country;
        int now = 0;
        int past = 0;
        int population;
        JSONObject numbers = null;
        CovidNumberExtractor numberExtractor = new CovidNumberExtractor();
        List<String> incidenceByCountry = new ArrayList<String>();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();

        for(int index = 0; index < countries.length; index++) {
            country = countries[index];
            numbers = numberExtractor.getNumbers(country).getJSONObject("All");
            population = numbers.getInt("population");
            JSONObject dates = numbers.getJSONObject("dates");
            now = dates.getInt(dateFormat.format(dateTime.minusDays(1)));
            past = dates.getInt(dateFormat.format(dateTime.minusDays(8)));
            incidenceByCountry.add(country + " : " + calculateIncidence(now, past, population));
        }
        return incidenceByCountry.toString();
    }

    private double calculateIncidence(int now, int past, int population){
        double newInfected = now - past;
        return (newInfected*100000)/population;
    }

}
