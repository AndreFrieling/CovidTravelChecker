package com.example.CovidTravelChecker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private CovidStatus covidStatus;

    @GetMapping(value = "", consumes = "application.json", produces = "plain/text")
    public ResponseEntity<String> sendGet(@org.springframework.web.bind.annotation.RequestBody RequestBody parameters){
        String answer = covidStatus.getStatus(parameters.getCountries());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answer);
    }
}
