/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.wildliferescuesystem;

/**
 *
 * @author Nto
 */
import java.util.Scanner;
public class WildLifeRescueSystem {

  




// Main console application handling user input, validation, and menu navigation

    private static Scanner scanner = new Scanner(System.in);
    private static RescueManager manager = new RescueManager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- WILDLIFE SA MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Injured Animal Rescue Case");
            System.out.println("2. Add Orphaned Animal Rescue Case");
            System.out.println("3. Add Endangered Species Rescue Case");
            System.out.println("4. Search Rescue Case");
            System.out.println("5. Update Rescue Status");
            System.out.println("6. Display Rescue Report");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addInjuredCase();
                    break;
                case "2":
                    addOrphanedCase();
                    break;
                case "3":
                    addEndangeredCase();
                    break;
                case "4":
                    searchCase();
                    break;
                case "5":
                    updateStatus();
                    break;
                case "6":
                    System.out.println("\n" + manager.generateReport());
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting application... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 7.");
            }
        }
    }

    // Validates that text inputs are not empty or blank[span_2](start_span)[span_2](end_span)
    private static String getNonEmptyInput(String prompt) {
        String value = "";
        while (value.trim().isEmpty()) {
            System.out.print(prompt);
            value = scanner.nextLine();
            if (value.trim().isEmpty()) {
                System.out.println("Error: Input cannot be blank.");
            }
        }
        return value.trim();
    }

    // Validates positive double values greater than zero[span_3](start_span)[span_3](end_span)
    private static double getPositiveDouble(String prompt) {
        double value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("Error: Value must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric value.");
            }
        }
        return value;
    }

    // Validates positive integer values greater than zero[span_4](start_span)[span_4](end_span)
    private static int getPositiveInt(String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("Error: Value must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
        return value;
    }

    // Ensures inputted Case ID is not blank and is unique[span_5](start_span)[span_5](end_span)
    private static String getUniqueCaseId() {
        while (true) {
            String id = getNonEmptyInput("Enter Rescue Case ID: ");
            if (manager.isIdUnique(id)) {
                return id;
            }
            System.out.println("Error: Rescue Case ID already exists. Enter a unique ID.");
        }
    }

    // Prompts and captures data for Injured Animal Rescue cases
    private static void addInjuredCase() {
        String id = getUniqueCaseId();
        String name = getNonEmptyInput("Enter Animal Name: ");
        String species = getNonEmptyInput("Enter Species: ");
        String location = getNonEmptyInput("Enter Rescue Location: ");
        String ranger = getNonEmptyInput("Enter Assigned Ranger: ");
        int days = getPositiveInt("Enter Number of Rescue Days: ");
        double dailyCost = getPositiveDouble("Enter Daily Care Cost: ");
        double medicalCost = getPositiveDouble("Enter Medical Cost: ");

        System.out.print("Does this animal require surgery? (yes/no): ");
        boolean surgery = scanner.nextLine().equalsIgnoreCase("yes");

        InjuredAnimalRescue caseObj = new InjuredAnimalRescue(id, name, species, location, ranger, days, dailyCost, medicalCost, surgery);
        manager.addRescueCase(caseObj);
        System.out.println("Injured animal rescue case added successfully!");
    }

    // Prompts and captures data for Orphaned Animal Rescue cases
    private static void addOrphanedCase() {
        String id = getUniqueCaseId();
        String name = getNonEmptyInput("Enter Animal Name: ");
        String species = getNonEmptyInput("Enter Species: ");
        String location = getNonEmptyInput("Enter Rescue Location: ");
        String ranger = getNonEmptyInput("Enter Assigned Ranger: ");
        int days = getPositiveInt("Enter Number of Rescue Days: ");
        double dailyCost = getPositiveDouble("Enter Daily Care Cost: ");
        double feedingCost = getPositiveDouble("Enter Feeding Cost: ");

        System.out.print("Does this animal require foster care? (yes/no): ");
        boolean foster = scanner.nextLine().equalsIgnoreCase("yes");

        OrphanedAnimalRescue caseObj = new OrphanedAnimalRescue(id, name, species, location, ranger, days, dailyCost, feedingCost, foster);
        manager.addRescueCase(caseObj);
        System.out.println("Orphaned animal rescue case added successfully!");
    }

    // Prompts and captures data for Endangered Species Rescue cases
    private static void addEndangeredCase() {
        String id = getUniqueCaseId();
        String name = getNonEmptyInput("Enter Animal Name: ");
        String species = getNonEmptyInput("Enter Species: ");
        String location = getNonEmptyInput("Enter Rescue Location: ");
        String ranger = getNonEmptyInput("Enter Assigned Ranger: ");
        int days = getPositiveInt("Enter Number of Rescue Days: ");
        double dailyCost = getPositiveDouble("Enter Daily Care Cost: ");
        String classification = getNonEmptyInput("Enter Conservation Classification: ");
        double securityCost = getPositiveDouble("Enter Security Cost: ");

        System.out.print("Is a specialist team required? (yes/no): ");
        boolean team = scanner.nextLine().equalsIgnoreCase("yes");

        EndangeredSpeciesRescue caseObj = new EndangeredSpeciesRescue(id, name, species, location, ranger, days, dailyCost, classification, securityCost, team);
        manager.addRescueCase(caseObj);
        System.out.println("Endangered species rescue case added successfully!");
    }

    // Searches for a case by ID and displays summary[span_6](start_span)[span_6](end_span)
    private static void searchCase() {
        String id = getNonEmptyInput("Enter Rescue Case ID to Search: ");
        RescueCase rc = manager.searchCase(id);
        if (rc != null) {
            System.out.println("\n--- CASE FOUND ---");
            System.out.println(rc.generateSummary());
        } else {
            System.out.println("Rescue case with ID '" + id + "' was not found.");
        }
    }

    // Updates case status (e.g. Start operation, Complete operation)[span_7](start_span)[span_7](end_span)
    private static void updateStatus() {
        String id = getNonEmptyInput("Enter Rescue Case ID to Update: ");
        RescueCase rc = manager.searchCase(id);
        if (rc != null) {
            System.out.println("Select New Status Option:");
            System.out.println("1. Start Rescue Operation");
            System.out.println("2. Complete Rescue Operation");
            System.out.println("3. Set Custom Status");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                rc.startRescue();
                System.out.println("Status updated to 'Rescue in Progress'.");
            } else if (choice.equals("2")) {
                rc.completeRescue();
                System.out.println("Status updated to 'Completed'.");
            } else if (choice.equals("3")) {
                String custom = getNonEmptyInput("Enter Custom Status: ");
                rc.setCurrentStatus(custom);
                System.out.println("Status updated.");
            } else {
                System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("Rescue case with ID '" + id + "' was not found.");
        }
    }
}
    

