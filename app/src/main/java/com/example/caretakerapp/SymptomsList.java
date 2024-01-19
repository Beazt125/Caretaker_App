package com.example.caretakerapp;

import java.util.ArrayList;
import java.util.List;

class Symptom {
    private final String name;
    private final String description;

    public Symptom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

public class SymptomsList {
    public static void main(String[] args) {
        List<Symptom> symptomsList = new ArrayList<>();

        symptomsList.add(new Symptom("Fever", "Elevated body temperature"));
        symptomsList.add(new Symptom("Cough", "Persistent coughing"));
        symptomsList.add(new Symptom("Headache", "Pain or discomfort in the head"));
        
        System.out.println("Symptoms List:");
        for (Symptom symptom : symptomsList) {
            System.out.println("Name: " + symptom.getName());
            System.out.println("Description: " + symptom.getDescription());
            System.out.println("------------");
        }
    }
}
