package com.example.medapp.service;

import com.example.medapp.model.Medicine;
import com.example.medapp.model.Symptom;
import com.example.medapp.repository.MedicineRepository;
import com.example.medapp.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Symptom> searchSymptoms(String query) {
        String q = query.toLowerCase();
        return symptomRepository.findAll().stream()
                .filter(s -> s.getName().toLowerCase().contains(q))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Medicine> recommendBySymptomIds(List<Long> symptomIds, int age) {
        Set<Medicine> meds = new HashSet<>();
        for (Long id : symptomIds) {
            Optional<Symptom> symOpt = symptomRepository.findById(id);
            symOpt.ifPresent(symptom -> meds.addAll(symptom.getMedicines()));
        }

        List<Medicine> list = new ArrayList<>(meds);
        list.sort(Comparator.comparing(Medicine::getName));
        return list;
    }
}
