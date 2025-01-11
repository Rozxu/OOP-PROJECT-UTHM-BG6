package Patient;

import Medication.Medication;
import Prescription.Prescription;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String condition; // Patient's condition or sickness
    private Prescription prescription; // Prescription assigned to the patient

    // Constructor
    public Patient(int id, String name, int age, String condition, Prescription prescription) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.prescription = prescription;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    // Display Patient and Prescription Details
    public void displayDetails() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Condition: " + condition);
        prescription.displayDetails(); // Polymorphic call to displayDetails
    }
}
