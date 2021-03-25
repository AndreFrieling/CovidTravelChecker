package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {

    private CovidStatus covidStatus = new CovidStatus();

    @GetMapping(value = "/incidence", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> sendGet(@org.springframework.web.bind.annotation.RequestBody RequestBody parameters, @RequestParam(required = false) String country) throws IOException, InterruptedException, JSONException {
        String[] countries;
        if(country!=null && country!=""){
            countries = new String[1];
            countries[0] = country;
        }else {
            countries = parameters.getCountries();
        }
        String answer = covidStatus.getStatus(countries);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answer);
    }
}
