package com.mycompany.wildliferescuesystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nto
 */

// Subclass for endangered wildlife with security and specialist needs
public class EndangeredSpeciesRescue extends RescueCase {
    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String caseId, String animalName, String species, String rescueLocation, 
                                   String assignedRanger, int numberOfDays, double dailyCareCost, 
                                   String conservationClassification, double securityCost, boolean specialistTeamRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, numberOfDays, dailyCareCost);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    // Calculates base costs, security, and R8 000 specialist team fee if required[span_2](start_span)[span_2](end_span)
    @Override
    public double calculateTotalCost() {
        double total = (getNumberOfDays() * getDailyCareCost()) + securityCost;
        if (specialistTeamRequired) {
            total += 8000.0; // Additional specialist fee[span_3](start_span)[span_3](end_span)
        }
        return total;
    }

    // Sets priority to Critical for critically endangered status or specialist needs
    @Override
    public String determinePriority() {
        if ("Critically Endangered".equalsIgnoreCase(conservationClassification) || specialistTeamRequired) {
            return "Critical";
        }
        return "High";
    }

    @Override
    public String getRescueType() {
        return "Endangered Species Rescue";
    }
}

