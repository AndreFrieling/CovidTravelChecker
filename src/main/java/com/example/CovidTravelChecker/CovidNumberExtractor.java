package com.example.CovidTravelChecker;

import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CovidNumberExtractor {
    public JSONObject getNumbers(String country) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-api.mmediagroup.fr/v1/history?country=" + country + "&status=confirmed"))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }

        JSONObject json;
        try {
            json = new JSONObject(response.body());
        }catch (org.json.JSONException e){
            e.printStackTrace();
            return new JSONObject();
        }
        return json;
    }
}
