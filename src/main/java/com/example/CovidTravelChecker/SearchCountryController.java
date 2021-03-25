package com.example.CovidTravelChecker;

import ch.qos.logback.core.joran.action.ActionConst;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class SearchCountryController {

    private static final Logger LOGGER = Logger.getLogger( SearchCountryController.class.getName() );

    private List<CountryIncidence> countryIncidenceList = new ArrayList<>();

    @RequestMapping("/home")
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
            LOGGER.log(Level.WARNING,"Country with name " + countryIncidence.getName() + " not Found");
            model.addAttribute("warning", "Country with name " + countryIncidence.getName() + " not Found");
            e.printStackTrace();
        }
        model.addAttribute("countryIncidenceList", countryIncidenceList);
        return "incidence";
    }
}
