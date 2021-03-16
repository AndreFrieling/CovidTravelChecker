package com.example.CovidTravelChecker;

import org.json.JSONArray;
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
        int now;
        int past;
        int population;
        List<String> listOfCountries = new ArrayList<String>();
        List<String> incidences = new ArrayList<>();

        JSONObject numbers = null;
        CovidNumberExtractor numberExtractor = new CovidNumberExtractor();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();

        for(int index = 0; index < countries.length; index++) {
            country = countries[index];
            numbers = numberExtractor.getNumbers(country);
            if(numbers.has("All")) {
                numbers = numbers.getJSONObject("All");
                population = numbers.getInt("population");
                JSONObject dates = numbers.getJSONObject("dates");
                now = dates.getInt(dateFormat.format(dateTime.minusDays(1)));
                past = dates.getInt(dateFormat.format(dateTime.minusDays(8)));
                incidences.add("" + calculateIncidence(now, past, population));
            }else{
                incidences.add("Not found. Make sure the country is spelled correctly (English)");
            }
            listOfCountries.add(country);
        }
        return createJson(incidences, listOfCountries).toString();
    }

    private double calculateIncidence(int now, int past, int population){
        double newInfected = now - past;
        return (newInfected*100000)/population;
    }

    private JSONObject createJson(List<String> incidences, List<String> countries){
        JSONObject allCountries = new JSONObject();
        JSONArray incidenceByCountry = new JSONArray();
        try {
            for (int i = 0; i < incidences.size(); i++) {
                JSONObject x = new JSONObject();
                x.put("country", countries.get(i));
                x.put("incidence", incidences.get(i));
                incidenceByCountry.put(x);
            }
            allCountries.put("All", incidenceByCountry);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return allCountries;
    }
}
