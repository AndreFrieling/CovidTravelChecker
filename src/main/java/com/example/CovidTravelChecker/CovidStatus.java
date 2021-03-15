package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CovidStatus {
    public JSONObject getStatus(String[] countries) throws IOException, InterruptedException, JSONException {
        String country;
        int now;
        int past;
        int population;
        List<String> listOfCountries = new ArrayList<String>();
        List<Double> incidences = new ArrayList<>();

        JSONObject numbers = null;
        CovidNumberExtractor numberExtractor = new CovidNumberExtractor();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();

        for(int index = 0; index < countries.length; index++) {
            country = countries[index];
            numbers = numberExtractor.getNumbers(country).getJSONObject("All");
            population = numbers.getInt("population");
            JSONObject dates = numbers.getJSONObject("dates");
            now = dates.getInt(dateFormat.format(dateTime.minusDays(1)));
            past = dates.getInt(dateFormat.format(dateTime.minusDays(8)));
            incidences.add(calculateIncidence(now, past, population));
            listOfCountries.add(country);
        }
        return createJson(incidences, listOfCountries);
    }

    private double calculateIncidence(int now, int past, int population){
        double newInfected = now - past;
        return (newInfected*100000)/population;
    }

    private JSONObject createJson(List<Double> sortedIncidences, List<String> sortedCountries){
        JSONObject json = new JSONObject();
        try {
            for (int i = 0; i < sortedIncidences.size(); i++) {
                json.put(sortedCountries.get(i), sortedIncidences.get(i));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
