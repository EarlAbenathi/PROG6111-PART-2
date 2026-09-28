/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nto
 */
package com.mycompany.wildliferescuesystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RescueManagerTest {
   


// Test suite verifying calculations, status updates, search, and duplicate prevention[span_1](start_span)[span_1](end_span)
    private RescueManager manager;

    @BeforeEach
    public void setUp() {
        manager = new RescueManager();
    }

    // Verifies orphaned rescue total cost calculation including foster care fee[span_2](start_span)[span_2](end_span)[span_3](start_span)[span_3](end_span)
    @Test
    public void testRescueCostCalculation_OrphanedWithFosterCare() {
        // (10 days * 500 daily cost) + 1500 feeding + 2500 foster care = 9000.00[span_4](start_span)[span_4](end_span)
        OrphanedAnimalRescue case1 = new OrphanedAnimalRescue(
            "WR102", "Rhino", "White Rhino", "Kruger Park", "Ranger John", 10, 500.0, 1500.0, true
        );
        assertEquals(9000.0, case1.calculateTotalCost(), 0.001);
    }

    // Verifies priority calculation for injured animal requiring surgery[span_5](start_span)[span_5](end_span)
    @Test
    public void testRescuePriorityCalculation_InjuredSurgery() {
        InjuredAnimalRescue case1 = new InjuredAnimalRescue(
            "WR101", "Elephant", "African Elephant", "Limpopo", "Ranger Dave", 5, 1000.0, 2000.0, true
        );
        assertEquals("Critical", case1.determinePriority());
    }

    // Verifies start, completion, and current status transitions[span_6](start_span)[span_6](end_span)[span_7](start_span)[span_7](end_span)
    @Test
    public void testRescueStatusUpdates() {
        OrphanedAnimalRescue case1 = new OrphanedAnimalRescue(
            "WR102", "Rhino", "White Rhino", "Kruger Park", "Ranger John", 10, 500.0, 1500.0, true
        );
        
        assertEquals("Pending", case1.getCurrentStatus());
        case1.startRescue();
        assertEquals("Rescue in Progress", case1.getCurrentStatus());
        case1.completeRescue();
        assertEquals("Completed", case1.getCurrentStatus());
    }

    // Verifies searching and retrieving an existing rescue case[span_8](start_span)[span_8](end_span)[span_9](start_span)[span_9](end_span)
    @Test
    public void testSearchExistingRescueCase() {
        EndangeredSpeciesRescue case1 = new EndangeredSpeciesRescue(
            "WR103", "Cheetah", "African Cheetah", "Kalahari", "Ranger Sam", 7, 800.0, "Vulnerable", 3000.0, false
        );
        manager.addRescueCase(case1);

        RescueCase found = manager.searchCase("WR103");
        assertNotNull(found);
        assertEquals("African Cheetah", found.getSpecies());
    }

    // Verifies duplicate Case IDs are rejected[span_10](start_span)[span_10](end_span)
    @Test
    public void testPreventDuplicateRescueCaseIDs() {
        OrphanedAnimalRescue case1 = new OrphanedAnimalRescue(
            "WR101", "Rhino", "White Rhino", "Kruger Park", "Ranger John", 10, 500.0, 1500.0, true
        );
        InjuredAnimalRescue case2 = new InjuredAnimalRescue(
            "WR101", "Elephant", "African Elephant", "Limpopo", "Ranger Dave", 5, 1000.0, 2000.0, false
        );

        assertTrue(manager.addRescueCase(case1));
        assertFalse(manager.isIdUnique("WR101"));
        assertFalse(manager.addRescueCase(case2));
        assertEquals(1, manager.getRescueCases().size());
    }
}

