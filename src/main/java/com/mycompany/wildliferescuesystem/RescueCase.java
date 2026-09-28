/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wildliferescuesystem;

/**
 *
 * @author Nto
 */import java.text.NumberFormat;
import java.util.Locale;

 



public abstract class RescueCase implements RescueOperation {
    private String caseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int numberOfDays;
    private double dailyCareCost;
    private String currentStatus;

    public RescueCase(String caseId, String animalName, String species, String rescueLocation, 
                      String assignedRanger, int numberOfDays, double dailyCareCost) {
        this.caseId = caseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfDays = numberOfDays;
        this.dailyCareCost = dailyCareCost;
        this.currentStatus = "Pending";
    }

    public abstract double calculateTotalCost();
    public abstract String determinePriority();
    public abstract String getRescueType();

    @Override
    public void startRescue() {
        this.currentStatus = "Rescue in Progress";
    }

    @Override
    public void completeRescue() {
        this.currentStatus = "Completed";
    }

    @Override
    public String generateSummary() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
        return "Case ID         : " + caseId + "\n" +
               "Type            : " + getRescueType() + "\n" +
               "Species         : " + species + "\n" +
               "Priority        : " + determinePriority() + "\n" +
               "Status          : " + currentStatus + "\n" +
               "Total Cost      : " + currencyFormat.format(calculateTotalCost());
    }

    public String getCaseId() { return caseId; }
    public String getAnimalName() { return animalName; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getNumberOfDays() { return numberOfDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
}

