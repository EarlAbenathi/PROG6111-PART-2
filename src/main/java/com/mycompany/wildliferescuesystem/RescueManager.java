/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wildliferescuesystem;

/**
 *
 * @author Nto
 */
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;




// Manages the list of rescue cases in memory and handles operations
public class RescueManager {
    // ArrayList to store all active rescue cases in memory[span_1](start_span)[span_1](end_span)
    private ArrayList<RescueCase> rescueCases;

    public RescueManager() {
        this.rescueCases = new ArrayList<>();
    }

    // Checks if a Rescue Case ID is unique across stored cases[span_2](start_span)[span_2](end_span)
    public boolean isIdUnique(String caseId) {
        for (RescueCase rc : rescueCases) {
            if (rc.getCaseId().equalsIgnoreCase(caseId)) {
                return false;
            }
        }
        return true;
    }

    // Adds a rescue case if non-null and ID is unique[span_3](start_span)[span_3](end_span)
    public boolean addRescueCase(RescueCase rescueCase) {
        if (rescueCase == null || !isIdUnique(rescueCase.getCaseId())) {
            return false;
        }
        return rescueCases.add(rescueCase);
    }

    // Searches for a case by its unique ID[span_4](start_span)[span_4](end_span)
    public RescueCase searchCase(String caseId) {
        for (RescueCase rc : rescueCases) {
            if (rc.getCaseId().equalsIgnoreCase(caseId)) {
                return rc;
            }
        }
        return null;
    }

    // Generates the formatted summary report for all rescue cases[span_5](start_span)[span_5](end_span)
    public String generateReport() {
        if (rescueCases.isEmpty()) {
            return "No rescue cases on record.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("*****************************************************\n");
        sb.append("WILDLIFE RESCUE REPORT\n");
        sb.append("*****************************************************\n\n");

        double totalCost = 0.0;

        for (int i = 0; i < rescueCases.size(); i++) {
            RescueCase rc = rescueCases.get(i);
            sb.append(rc.generateSummary()).append("\n");
            totalCost += rc.calculateTotalCost();

            if (i < rescueCases.size() - 1) {
                sb.append("-----------------------------------------------------\n");
            }
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));

        sb.append("*****************************************************\n");
        sb.append("Total Rescue Cases : ").append(rescueCases.size()).append("\n");
        sb.append("Total Rescue Cost  : ").append(currencyFormat.format(totalCost)).append("\n");

        return sb.toString();
    }

    // Returns the underlying ArrayList of cases
    public ArrayList<RescueCase> getRescueCases() {
        return rescueCases;
    }
}

