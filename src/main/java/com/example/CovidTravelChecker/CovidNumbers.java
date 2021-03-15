package com.example.CovidTravelChecker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovidNumbers {
    @JsonProperty
    String country;
    @JsonProperty
    int population;
    @JsonProperty
    int[] dates;
}
