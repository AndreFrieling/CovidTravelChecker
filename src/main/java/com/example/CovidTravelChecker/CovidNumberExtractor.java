package com.example.CovidTravelChecker;

import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CovidNumberExtractor {
    public JSONObject getNumbers(String country) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-api.mmediagroup.fr/v1/history?country=" + country + "&status=confirmed"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = null;
        try {
            json = new JSONObject(response.body());
        }catch (org.json.JSONException e){
            System.out.println(e);
        }
        return json;
    }
}
