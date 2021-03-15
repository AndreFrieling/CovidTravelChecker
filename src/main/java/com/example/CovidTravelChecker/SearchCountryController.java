package com.example.CovidTravelChecker;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchCountryController {

    private List<CountryIncidence> countryIncidenceList = new ArrayList<>();

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/incidence")
    public String showForm(Model model) {
        CountryIncidence countryIncidence = new CountryIncidence();
        model.addAttribute("countryIncidence", countryIncidence);
        return "incidence";
    }

    @PostMapping("/incidence")
    public String submitForm(@ModelAttribute("countryIncidence") CountryIncidence countryIncidence, Model model) {
        GetSingleCountryHandler helper = new GetSingleCountryHandler();
        try {
            countryIncidenceList.add(helper.addIncidence(countryIncidence));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        model.addAttribute("countryIncidenceList", countryIncidenceList);
        return "incidence";
    }
}
