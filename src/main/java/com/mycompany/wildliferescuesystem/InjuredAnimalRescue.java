/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wildliferescuesystem;

/**
 *
 * @author Nto
 */

// Subclass representing injured wildlife requiring medical care
public class InjuredAnimalRescue extends RescueCase {
    private double medicalCost;
    private boolean requiresSurgery;

    public InjuredAnimalRescue(String caseId, String animalName, String species, String rescueLocation, 
                                String assignedRanger, int numberOfDays, double dailyCareCost, 
                                double medicalCost, boolean requiresSurgery) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, numberOfDays, dailyCareCost);
        this.medicalCost = medicalCost;
        this.requiresSurgery = requiresSurgery;
    }

    // Calculates daily care, medical, and optional surgery costs
    @Override
    public double calculateTotalCost() {
        double total = (getNumberOfDays() * getDailyCareCost()) + medicalCost;
        if (requiresSurgery) {
            total += 5000.0; // Fixed surgery fee
        }
        return total;
    }

    // Determines urgency based on surgical need and medical cost
    @Override
    public String determinePriority() {
        if (requiresSurgery) {
            return "Critical";
        } else if (medicalCost > 3000) {
            return "High";
        }
        return "Medium";
    }

    @Override
    public String getRescueType() {
        return "Injured Animal Rescue";
    }
}