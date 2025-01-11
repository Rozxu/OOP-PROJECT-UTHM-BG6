package Prescription;

import Medication.Medication;

public abstract class Prescription {
    protected int prescriptionId;
    protected Medication medication;
    protected int quantity;
    protected int stockAtPrescriptionTime; // New field to store stock at the time of prescription

    // Constructor
    public Prescription(int prescriptionId, Medication medication, int quantity) {
        this.prescriptionId = prescriptionId;
        this.medication = medication;
        this.quantity = quantity;
        this.stockAtPrescriptionTime = medication.getStockQuantity(); // Record stock at the time of prescription
    }

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public Medication getMedication() {
        return medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStockAtPrescriptionTime() { // Getter for stock at the time of prescription
        return stockAtPrescriptionTime;
    }

    // Abstract method for displaying prescription details
    public abstract void displayDetails();
}
