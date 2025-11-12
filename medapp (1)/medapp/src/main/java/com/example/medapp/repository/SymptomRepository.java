package com.example.medapp.repository;

import com.example.medapp.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Symptom findByNameIgnoreCase(String name);
}
