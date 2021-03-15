package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetSingleCountryHandler {

    public CountryIncidence addIncidence(CountryIncidence countryObject) throws IOException, InterruptedException, JSONException {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();

        CovidNumberExtractor numberExtractor = new CovidNumberExtractor();
        JSONObject numbers = numberExtractor.getNumbers(countryObject.getName()).getJSONObject("All");
        int population = numbers.getInt("population");
        JSONObject dates = numbers.getJSONObject("dates");
        int now = dates.getInt(dateFormat.format(dateTime.minusDays(1)));
        int past = dates.getInt(dateFormat.format(dateTime.minusDays(8)));
        double incidence = calculateIncidence(now, past, population);

        countryObject.setIncidence(incidence);
        return countryObject;
    }

    private double calculateIncidence(int now, int past, int population){
        double newInfected = now - past;
        return (newInfected*100000)/population;
    }
}
