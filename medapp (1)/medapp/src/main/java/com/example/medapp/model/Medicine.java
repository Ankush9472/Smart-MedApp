package com.example.medapp.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "adult_dosage") // 👈 map to correct DB column
    private String adultDose;

    @Column(name = "child_dosage") // 👈 map to correct DB column
    private String childDose;

    @ManyToMany(mappedBy = "medicines")
    private Set<Symptom> symptoms;

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdultDose() {
        return adultDose;
    }

    public void setAdultDose(String adultDose) {
        this.adultDose = adultDose;
    }

    public String getChildDose() {
        return childDose;
    }

    public void setChildDose(String childDose) {
        this.childDose = childDose;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
