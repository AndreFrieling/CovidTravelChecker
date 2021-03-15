package com.example.CovidTravelChecker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
//import sun.net.www.http.HttpClient;

import java.net.URI;
import java.net.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class CovidNumberExtractor {
    URL url;
    JSONObject jsonAnswer;
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
