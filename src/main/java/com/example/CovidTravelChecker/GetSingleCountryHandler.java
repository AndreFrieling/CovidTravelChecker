package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        double incidence = new BigDecimal(calculateIncidence(now, past, population)).setScale(2, RoundingMode.HALF_UP).doubleValue();

        countryObject.setIncidence(incidence);
        countryObject.setColor(getColor(countryObject));
        return countryObject;
    }

    private String calculateIncidence(int now, int past, int population){
        double newInfected = now - past;
        return "" + (newInfected*100000)/population;
    }

    private String getColor(CountryIncidence countryObject){
        if (countryObject.getIncidence() < 35){
            return "color: green;";
        } else if(35 <=countryObject.getIncidence() && countryObject.getIncidence() < 50){
            return "color: orange;";
        } else if(50 <=countryObject.getIncidence() && countryObject.getIncidence() < 100){
            return "color: red;";
        } else if(100 <=countryObject.getIncidence()){
            return "color: darkred;";
        }
        return "";
    }
}
