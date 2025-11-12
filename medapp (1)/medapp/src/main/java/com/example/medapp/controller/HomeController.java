package com.example.medapp.controller;

import com.example.medapp.model.Medicine;
import com.example.medapp.model.Symptom;
import com.example.medapp.repository.SymptomRepository;
import com.example.medapp.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private SymptomRepository symptomRepository;

    private final String doctorNumber = "+91-0000000000";

    @GetMapping("/")
    public String index(Model model) {
        List<Symptom> symptoms = symptomRepository.findAll();
        model.addAttribute("symptoms", symptoms);
        model.addAttribute("doctorNumber", doctorNumber);
        return "index";
    }

    @GetMapping("/recommend")
    public String recommend(@RequestParam(name = "symptom", required = false) String symptomName,
            @RequestParam(name = "age") int age,
            Model model) {

        if (symptomName == null || symptomName.isEmpty()) {
            model.addAttribute("noSelection", true);
            model.addAttribute("doctorNumber", doctorNumber);
            model.addAttribute("symptoms", symptomRepository.findAll());
            return "index";
        }

        // Find symptom by name
        Optional<Symptom> symptomOpt = symptomRepository.findAll().stream()
                .filter(s -> s.getName().equalsIgnoreCase(symptomName))
                .findFirst();

        if (symptomOpt.isEmpty()) {
            model.addAttribute("noMatch", true);
            model.addAttribute("doctorNumber", doctorNumber);
            return "index";
        }

        List<Long> symptomIds = List.of(symptomOpt.get().getId());
        List<Medicine> meds = recommendationService.recommendBySymptomIds(symptomIds, age);

        model.addAttribute("medicines", meds);
        model.addAttribute("age", age);
        model.addAttribute("symptom", symptomName);
        model.addAttribute("doctorNumber", doctorNumber);

        if (meds.isEmpty()) {
            model.addAttribute("noMatch", true);
        }

        return "result";
    }

    @GetMapping("/emergency")
    public String emergency(Model model) {
        Map<String, String> contacts = new LinkedHashMap<>();
        contacts.put("Ambulance", "102");
        contacts.put("Police", "100");
        contacts.put("Fire", "101");
        contacts.put("National Emergency / Help Line", "112");
        model.addAttribute("contacts", contacts);
        return "emergency";
    }
}
