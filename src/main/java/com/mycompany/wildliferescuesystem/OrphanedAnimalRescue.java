/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wildliferescuesystem;

/**
 *
 * @author Nto
 */
  

// Subclass representing orphaned young animals needing feeding and care
public class OrphanedAnimalRescue extends RescueCase {
    private double feedingCost;
    private boolean requiresFosterCare;

    public OrphanedAnimalRescue(String caseId, String animalName, String species, String rescueLocation, 
                                 String assignedRanger, int numberOfDays, double dailyCareCost, 
                                 double feedingCost, boolean requiresFosterCare) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, numberOfDays, dailyCareCost);
        this.feedingCost = feedingCost;
        this.requiresFosterCare = requiresFosterCare;
    }

    // Calculates base care, food, and R2 500 foster care fee if required[span_0](start_span)[span_0](end_span)
    @Override
    public double calculateTotalCost() {
        double total = (getNumberOfDays() * getDailyCareCost()) + feedingCost;
        if (requiresFosterCare) {
            total += 2500.0; // Additional foster fee[span_1](start_span)[span_1](end_span)
        }
        return total;
    }

    // Determines urgency based on foster status or long stay duration
    @Override
    public String determinePriority() {
        if (requiresFosterCare || getNumberOfDays() > 30) {
            return "High";
        }
        return "Medium";
    }

    @Override
    public String getRescueType() {
        return "Orphaned Animal Rescue";
    }
}

