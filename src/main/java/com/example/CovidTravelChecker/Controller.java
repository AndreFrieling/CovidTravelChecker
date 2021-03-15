package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {

    private CovidStatus covidStatus = new CovidStatus();

    @GetMapping(value = "", consumes = "application/json", produces = "plain/text")
    public ResponseEntity<String> sendGet(@org.springframework.web.bind.annotation.RequestBody RequestBody parameters) throws IOException, InterruptedException, JSONException {
        String[] countries = parameters.getCountries();
        String answer = covidStatus.getStatus(countries);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answer);
    }
}
