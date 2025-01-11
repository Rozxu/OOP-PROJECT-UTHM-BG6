package Pharmacy;

import java.util.Scanner;
import java.util.ArrayList;

import Medication.*;
import Patient.*;
import Prescription.*;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        ArrayList<Medication> medicationList = new ArrayList<>(); 
        ArrayList<Patient> patientList = new ArrayList<>(); 
        
        int menu;
        int autoIndexMed = 1;
        int autoIndexPat = 1;
        int autoIndexPresc = 1;

        while (true) { 
            System.out.println(" _________________________________________________________");
            System.out.println("|                                                         |");
            System.out.println("|          ~ WELCOME TO HOSPITAL PHARMACY SYSTEM ~        |");
            System.out.println("|_________________________________________________________|");
            System.out.println("|                                                         |");
            System.out.println("|             >> 1. ADD NEW MEDICATION                    |");
            System.out.println("|             >> 2. UPDATE MEDICATION STOCK               |");
            System.out.println("|             >> 3. DELETE MEDICATION STOCK               |");
            System.out.println("|             >> 4. UPDATE MEDICATION DATA                |");            
            System.out.println("|             >> 5. VIEW ALL MEDICATION                   |");
            System.out.println("|             >> 6. ADD & PRESCRIBE PATIENT               |");
            System.out.println("|             >> 7. PATIENT PRESCRIPTION HISTORY          |");
            System.out.println("|             >> 8. EXIT                                  |");
            System.out.println("|_________________________________________________________|");

            System.out.print("\n                  ENTER A NUMBER : ");

            if (!input.hasNextInt()) { 
                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("           INVALID INPUT. PLEASE ENTER A NUMBER.");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                input.next(); 
                continue; 
            }

            menu = input.nextInt();

            if (menu < 1 || menu > 8) { 
                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("           CHOICE IS INVALID, PLEASE TRY AGAIN");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                continue; 
            }

            switch (menu) {
                case 1:
                    // Add Medication Details
                while (true) {
                    System.out.println(" __________________________________________________________");

                    int index = autoIndexMed++;

                    input.nextLine(); 
                    System.out.print("\n\t> MEDICATION NAME: ");
                    String name = input.nextLine();

                    System.out.print("\n\t> MEDICATION TYPE (e.g., Tablet, Syrup): ");
                    String type = input.nextLine();

                    System.out.print("\n\t> MANUFACTURER: ");
                    String manufacturer = input.nextLine();

                    // Validate stockQuantity input (must be an integer)
                    int stockQuantity = -1;
                    while (stockQuantity < 0) {
                        System.out.print("\n\t> STOCK QUANTITY: ");
                        if (input.hasNextInt()) {
                            stockQuantity = input.nextInt();
                            if (stockQuantity < 0) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("   ERROR: STOCK QUANTITY CANNOT BE NEGATIVE. TRY AGAIN.");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            }
                        } else {
                            System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("   ERROR: PLEASE ENTER A VALID INTEGER FOR STOCK QUANTITY.");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            input.next(); // Clear the invalid input
                        }
                    }

                    // Validate price input (must be a double)
                    double price = -1;
                    while (price < 0) {
                        System.out.print("\n\t> PRICE(RM): ");
                        if (input.hasNextDouble()) {
                            price = input.nextDouble();
                            if (price < 0) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("   ERROR: PRICE CANNOT BE NEGATIVE. TRY AGAIN.");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            }
                        } else {
                            System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("   ERROR: PLEASE ENTER A VALID NUMBER FOR PRICE.");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            input.next(); // Clear the invalid input
                        }
                    }

                    // Check for duplicate index
                    boolean isDuplicate = false;
                    for (Medication med : medicationList) {
                        if (med.getIndex() == index) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("     ERROR: MEDICATION WITH THIS INDEX ALREADY EXISTS.      ");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                    } else {
                        Medication medication = new Medication(index, name, type, manufacturer, stockQuantity, price);

                        medicationList.add(medication);

                        System.out.println("\n-------------------------------MEDICATION HAS BEEN INSERTED--------------------------------");
                        System.out.println("___________________________________________________________________________________________\n");

                        medication.displayDetailsAsTableHeader();        
                        medication.displayDetailsAsTableRow();

                        System.out.println("___________________________________________________________________________________________\n");
                    }

                    System.out.print("\nDo you want to add another medication? (yes or type no to go back to main menu): ");
                    String response = input.next();
                    while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no"))) {
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("          WRONG INPUT. TRY AGAIN (yes or no) ");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                        System.out.print("\nDo you want to add another medication? (yes or type no to go back to main menu): ");
                        response = input.next();
                    }
                    if (response.equalsIgnoreCase("no")) {
                        break;
                    }
                }
                break;

                    
                case 2:
                    // Update medication stock
                    while (true) {
                        if (medicationList.isEmpty()) {
                            System.out.println("\n _________________________________________________________");
                            System.out.println("|                                                         |");
                            System.out.println("|           ~ NO MEDICATIONS AVAILABLE TO UPDATE ~        |");
                            System.out.println("|_________________________________________________________|");
                            break;
                        } else {
                            System.out.println("\n-------------------------------LIST OF ALL MEDICATIONS-----------------------------------");
                            System.out.println("___________________________________________________________________________________________\n");

                            for (Medication med : medicationList) {
                                if (medicationList.indexOf(med) == 0) { // Display header only once
                                    med.displayDetailsAsTableHeader();
                                }
                                med.displayDetailsAsTableRow();
                            }

                            System.out.println("___________________________________________________________________________________________\n");

                            String inputIndex;
                            while (true) {
                                System.out.print("Enter the MEDICATION INDEX to update (or type 'back' to go back): ");
                                inputIndex = input.next();

                                if (inputIndex.equalsIgnoreCase("back")) {
                                    break;
                                }

                                try {
                                    int updateIndex = Integer.parseInt(inputIndex);  // Try to parse input to integer
                                    boolean medicationFound = false;
                                    Medication medicationToUpdate = null;

                                    // Check if the medication exists
                                    for (Medication med : medicationList) {
                                        if (med.getIndex() == updateIndex) {
                                            medicationToUpdate = med;
                                            medicationFound = true;
                                            break;
                                        }
                                    }

                                    if (!medicationFound) {
                                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                        System.out.println("     ERROR: MEDICATION WITH THIS INDEX DOES NOT EXIST.    ");
                                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                    } else {
                                        // Update the medication details
                                        int newStockQuantity = -1;
                                        while (newStockQuantity < 0) {
                                            System.out.print("\nEnter new STOCK QUANTITY: ");
                                            if (input.hasNextInt()) {
                                                newStockQuantity = input.nextInt();
                                                if (newStockQuantity < 0) {
                                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                                    System.out.println("   ERROR: STOCK QUANTITY CANNOT BE NEGATIVE. TRY AGAIN.");
                                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                                }
                                            } else {
                                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                                System.out.println("   ERROR: PLEASE ENTER A VALID INTEGER FOR STOCK QUANTITY.");
                                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                                input.next(); // Clear the invalid input
                                            }
                                        }

                                        double newPrice = -1;
                                        while (newPrice < 0) {
                                            System.out.print("Enter new PRICE(RM): ");
                                            if (input.hasNextDouble()) {
                                                newPrice = input.nextDouble();
                                                if (newPrice < 0) {
                                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                                    System.out.println("   ERROR: PRICE CANNOT BE NEGATIVE. TRY AGAIN.");
                                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                                }
                                            } else {
                                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                                System.out.println("   ERROR: PLEASE ENTER A VALID NUMBER FOR PRICE.");
                                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                                input.next(); // Clear the invalid input
                                            }
                                        }

                                        medicationToUpdate.setStockQuantity(newStockQuantity);
                                        medicationToUpdate.setPrice(newPrice);

                                        System.out.println("\n-----------------------------MEDICATION HAS BEEN UPDATED---------------------------------");
                                        System.out.println("___________________________________________________________________________________________\n");

                                        medicationToUpdate.displayDetailsAsTableHeader();
                                        medicationToUpdate.displayDetailsAsTableRow();

                                        System.out.println("___________________________________________________________________________________________\n");
                                    }
                                    break;  // Exit the loop once a valid index is processed
                                } catch (NumberFormatException e) {
                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID NUMBER FOR MEDICATION INDEX.   ");
                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                }
                            }

                            System.out.print("\nDo you want to update another medication? (yes or type no to go back to main menu): ");
                            String response = input.next();
                            while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no"))) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("            WRONG INPUT. TRY AGAIN (yes or no).");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                response = input.next();
                            }
                            if (response.equalsIgnoreCase("no")) {
                                break;
                            }
                        }
                    }
                    break;
                    
                case 3:
                    // Delete medication stock
                    while (true) {
                        if (medicationList.isEmpty()) {
                            System.out.println("\n _________________________________________________________");
                            System.out.println("|                                                         |");
                            System.out.println("|           ~ NO MEDICATIONS AVAILABLE TO DELETE ~        |");
                            System.out.println("|_________________________________________________________|");
                            break;
                        } else {
                            System.out.println("\n-------------------------------LIST OF ALL MEDICATIONS-----------------------------------");
                            System.out.println("___________________________________________________________________________________________\n");

                            for (Medication med : medicationList) {
                                if (medicationList.indexOf(med) == 0) { 
                                    med.displayDetailsAsTableHeader();
                                }
                                med.displayDetailsAsTableRow();
                            }

                            System.out.println("___________________________________________________________________________________________\n");

                            String inputIndex;
                            while (true) {
                                System.out.print("Enter the MEDICATION INDEX to delete (or type 'back' to go back): ");
                                inputIndex = input.next();

                                if (inputIndex.equalsIgnoreCase("back")) {
                                    break;
                                }

                                try {
                                    int deleteIndex = Integer.parseInt(inputIndex);

                                    boolean medicationFoundToDelete = false;
                                    Medication medicationToDelete = null;

                                    // Find the medication to delete
                                    for (Medication med : medicationList) {
                                        if (med.getIndex() == deleteIndex) {
                                            medicationToDelete = med;
                                            medicationFoundToDelete = true;
                                            break;
                                        }
                                    }

                                    if (!medicationFoundToDelete) {
                                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                        System.out.println("     ERROR: MEDICATION WITH THIS INDEX DOES NOT EXIST.    ");
                                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                    } else {
                                        // Remove the medication from the list
                                        medicationList.remove(medicationToDelete);

                                        // Re-index the remaining medications
                                        for (int i = 0; i < medicationList.size(); i++) {
                                            medicationList.get(i).setIndex(i + 1);
                                        }

                                        // Update autoIndex to match the next available index
                                        autoIndexMed = medicationList.size() + 1;

                                        System.out.println("\n-----------------------------MEDICATION HAS BEEN DELETED---------------------------------");
                                        System.out.println("___________________________________________________________________________________________\n");

                                        if (medicationList.isEmpty()) {
                                            System.out.println("\n                   ~ NO MEDICATIONS LEFT IN THE LIST ~\n");
                                        } else {
                                            for (Medication med : medicationList) {
                                                if (medicationList.indexOf(med) == 0) { // Display header only once
                                                    med.displayDetailsAsTableHeader();
                                                }
                                                med.displayDetailsAsTableRow();
                                            }
                                        }

                                        System.out.println("___________________________________________________________________________________________\n");
                                    }
                                    break;  // Exit the loop once a valid index is processed
                                } catch (NumberFormatException e) {
                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID NUMBER FOR MEDICATION INDEX.   ");
                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                }
                            }

                            // Ask if the user wants to delete another medication
                            System.out.print("\nDo you want to delete another medication? (yes or type no to go back to main menu): ");
                            String response = input.next();
                            while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no"))) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("          WRONG INPUT. TRY AGAIN (yes or no).");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                response = input.next();
                            }
                            if (response.equalsIgnoreCase("no")) {
                                break;
                            }
                        }
                    }

            case 4:
                // Update Medicication Data 
                while (true) {
                    if (medicationList.isEmpty()) {
                        System.out.println("\n _________________________________________________________");
                        System.out.println("|                                                         |");
                        System.out.println("|           ~ NO MEDICATIONS AVAILABLE TO UPDATE ~        |");
                        System.out.println("|_________________________________________________________|");
                        break;
                    } else {
                        System.out.println("\n-------------------------------LIST OF ALL MEDICATIONS-----------------------------------");
                        System.out.println("___________________________________________________________________________________________\n");

                        for (Medication med : medicationList) {
                            if (medicationList.indexOf(med) == 0) { 
                                med.displayDetailsAsTableHeader();
                            }
                            med.displayDetailsAsTableRow(); 
                        }

                        System.out.println("___________________________________________________________________________________________\n");

                        Medication medicationToUpdate = null;

                        while (medicationToUpdate == null) {
                            System.out.print("Enter the MEDICATION INDEX to update (or type 'back' to go back): ");
                            String inputIndex = input.next();

                            if (inputIndex.equalsIgnoreCase("back")) {
                                break;  // Go back to the main menu, exit the current loop
                            }

                            try {
                                int updateIndex = Integer.parseInt(inputIndex);

                                for (Medication med : medicationList) {
                                    if (med.getIndex() == updateIndex) {
                                        medicationToUpdate = med;
                                        break;
                                    }
                                }

                                if (medicationToUpdate == null) {
                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    System.out.println("     ERROR: MEDICATION WITH THIS INDEX DOES NOT EXIST.    ");
                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID NUMBER.   ");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            }
                        }

                        if (medicationToUpdate != null) {  // Proceed only if a valid medication was selected
                            input.nextLine();  // Clear the buffer
                            System.out.print("\n\t> Enter new MEDICATION NAME (leave blank to keep current): ");
                            String newName = input.nextLine();
                            System.out.print("\n\t> Enter new MEDICATION TYPE (leave blank to keep current): ");
                            String newType = input.nextLine();
                            System.out.print("\n\t> Enter new MANUFACTURER (leave blank to keep current): ");
                            String newManufacturer = input.nextLine();

                            // Handle new stock quantity input with error checking for valid integer
                            int newStockQuantity = -1;
                            while (true) {
                                System.out.print("\n\t> Enter new STOCK QUANTITY (-1 to keep current): ");
                                try {
                                    newStockQuantity = input.nextInt();
                                    break;  // Valid input, exit loop
                                } catch (InputMismatchException e) {
                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID INTEGER FOR STOCK QUANTITY. ");
                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    input.nextLine();  // Clear the invalid input
                                }
                            }

                            // Handle new price input with error checking for valid double
                            double newPrice = -1;
                            while (true) {
                                System.out.print("\n\t> Enter new PRICE(RM) (-1 to keep current): ");
                                try {
                                    newPrice = input.nextDouble();
                                    break;  // Valid input, exit loop
                                } catch (InputMismatchException e) {
                                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID NUMBER FOR PRICE. ");
                                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                    input.nextLine();  // Clear the invalid input
                                }
                            }

                            if (!newName.isBlank()) medicationToUpdate.setName(newName);
                            if (!newType.isBlank()) medicationToUpdate.setType(newType);
                            if (!newManufacturer.isBlank()) medicationToUpdate.setManufacturer(newManufacturer);
                            if (newStockQuantity != -1) medicationToUpdate.setStockQuantity(newStockQuantity);
                            if (newPrice != -1) medicationToUpdate.setPrice(newPrice);

                            System.out.println("\n------------------------------MEDICATION DATA HAS BEEN UPDATED---------------------------------");
                            System.out.println("___________________________________________________________________________________________\n");

                            medicationToUpdate.displayDetailsAsTableHeader();
                            medicationToUpdate.displayDetailsAsTableRow();

                            System.out.println("___________________________________________________________________________________________\n");
                        }
                    }

                    System.out.print("\nDo you want to update another medication? (yes or type no to go back to main menu): ");
                    String response = input.next();
                    while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no"))) {
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("            WRONG INPUT. TRY AGAIN (yes or no).");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                        response = input.next();
                    }
                    if (response.equalsIgnoreCase("no")) {
                        break;  // Exit the loop and go back to the main menu
                    }
                }
                break;

                case 5:
                    // View all medications
                    if (medicationList.isEmpty()) {
                        System.out.println("\n _________________________________________________________");
                        System.out.println("|                                                         |");
                        System.out.println("|           ~ THERE IS NO MEDICATION RECORD ~             |");
                        System.out.println("|_________________________________________________________|");

                    } else {
                        System.out.println("\n-------------------------------LIST OF ALL MEDICATIONS-----------------------------------");
                        System.out.println("___________________________________________________________________________________________\n");

                        for (Medication med : medicationList) {
                            if (medicationList.indexOf(med) == 0) { 
                                med.displayDetailsAsTableHeader();
                            }
                            med.displayDetailsAsTableRow(); 
                        }
                        
                        System.out.println("___________________________________________________________________________________________\n");  
                    }
                    break;
               
                case 6:
    // Add & Prescribe Patient
    System.out.println(" __________________________________________________________");

    // Loop for adding multiple patients
    boolean addNewPatient = true;
    while (addNewPatient) {

        // Automatically generate patient ID
        int id = autoIndexPat++;

        // Check if medication list is empty
        if (medicationList.isEmpty()) {
            System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("        ERROR: NO MEDICATION AVAILABLE IN THE SYSTEM.       ");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
            break; // Exit the loop since no medications exist
        }

        input.nextLine(); // Clear the scanner buffer

        // Get Patient Name
        System.out.print("\n\t> PATIENT NAME: ");
        String name = input.nextLine();

        // Get Patient Age
        System.out.print("\n\t> PATIENT AGE: ");
        int age = -1;
        while (age <= 0) {
            try {
                age = input.nextInt();
                if (age <= 0) {
                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("    ERROR: AGE MUST BE A POSITIVE INTEGER. PLEASE TRY AGAIN.");
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                System.out.println("    ERROR: INVALID INPUT. PLEASE ENTER A VALID INTEGER FOR AGE.");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                input.nextLine(); // Clear the invalid input
            }
        }

        input.nextLine(); // Clear the scanner buffer again

        // Get Patient Condition
        System.out.print("\n\t> PATIENT CONDITION/SICKNESS: ");
        String condition = input.nextLine();

        System.out.println(" __________________________________________________________");

        // Display available medications
        System.out.println("\nAvailable Medications:");
        for (Medication med : medicationList) {
            if (medicationList.indexOf(med) == 0) {
                med.displayDetailsAsTableHeader();
            }
            med.displayDetailsAsTableRow();
        }

        boolean addMoreMedications = true;
        while (addMoreMedications) {
            Medication medicationNeeded = null;

            // Prompt user for medication index or option to go back
            while (true) {
                System.out.print("\n> Enter MEDICATION INDEX needed for patient OR TYPE 'back' to go to main menu: ");
                String medInput = input.nextLine();

                if (medInput.equalsIgnoreCase("back")) {
                    addNewPatient = false;  // Set outer loop flag to false to exit patient addition
                    addMoreMedications = false; // Exit medication loop
                    break;  // Break out of the inner loop and return to the main menu
                }

                try {
                    int medIndex = Integer.parseInt(medInput);

                    // Check if medication exists
                    for (Medication med : medicationList) {
                        if (med.getIndex() == medIndex) {
                            medicationNeeded = med;
                            break;
                        }
                    }

                    // Handle invalid medication index
                    if (medicationNeeded == null) {
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("        ERROR: INVALID MEDICATION INDEX SELECTED.           ");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                    } else if (medicationNeeded.getStockQuantity() == 0) {
                        // Check if the selected medication is out of stock
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("        ERROR: THIS MEDICATION IS OUT OF STOCK.            ");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                    } else {
                        break;  // Only break if medication is valid and in stock
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("   ERROR: INVALID INPUT. ENTER AN INDEX NUMBER OR 'back'.");
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                }
            }

            if (addNewPatient == false) {
                break; // Exit the outer loop if "back" was entered
            }

            if (medicationNeeded != null && medicationNeeded.getStockQuantity() > 0) {
                // Now, create a prescription for the patient
                int quantity = -1;
                while (quantity <= 0 || quantity > medicationNeeded.getStockQuantity()) {
                    System.out.print("\n\t> ENTER QUANTITY PRESCRIBED: ");
                    while (true) {
                        try {
                            quantity = input.nextInt();
                            if (quantity <= 0 || quantity > medicationNeeded.getStockQuantity()) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("  ERROR: Quantity exceeds available stock or is invalid. Please enter a valid quantity.");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            } else {
                                break; // Valid quantity, exit the inner loop
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("     ERROR: PLEASE ENTER A VALID INTEGER FOR QUANTITY.");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            System.out.print("\n\t> ENTER QUANTITY PRESCRIBED: ");
                            input.nextLine(); // Clear invalid input
                        }
                    }
                }

                // Create a BasicPrescription (could be extended to other prescription types)
                Prescription prescription = new BasicPrescription(autoIndexPresc++, medicationNeeded, quantity);

                // Update the medication stock after prescription
                medicationNeeded.updatePresStock(quantity);  // Update the stock of the medication

                // Add prescription to patient
                Patient patient = new Patient(id, name, age, condition, prescription);
                patientList.add(patient);

                System.out.println("\n-------------------------------PATIENT HAS BEEN INSERTED--------------------------------");
                System.out.println("___________________________________________________________________________________________\n");

                // Display Patient Details
                patient.displayDetails(); // Polymorphic call to displayDetails

                System.out.println("___________________________________________________________________________________________\n");

                // Ask user if they want to add more medications for this patient
                System.out.print("\nDo you want to add more medications for this patient? (yes or no): ");
                String moreMedicationsResponse = input.next();
                input.nextLine();

                while (!(moreMedicationsResponse.equalsIgnoreCase("yes") || moreMedicationsResponse.equalsIgnoreCase("no"))) {
                    System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("          WRONG INPUT. TRY AGAIN (yes or no) ");
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                    System.out.print("\nDo you want to add more medications for this patient? (yes or no): ");
                    moreMedicationsResponse = input.next();
                }

                addMoreMedications = moreMedicationsResponse.equalsIgnoreCase("yes");

                // After adding all prescriptions, ask if the user wants to add another patient
                if (!addMoreMedications) {
                    System.out.print("\nDo you want to add another patient? (yes or type no to go back to main menu): ");
                    String response = input.next();
                    while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no"))) {
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("          WRONG INPUT. TRY AGAIN (yes or no) ");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                        System.out.print("\nDo you want to add another patient? (yes or type no to go back to main menu): ");
                        response = input.next();
                    }
                    if (response.equalsIgnoreCase("no")) {
                        addNewPatient = false; // Exit the loop to return to main menu
                    }
                }
            }
        }
    }
    break;

                    
                case 7:
                    System.out.println(" __________________________________________________________");

                    // Check if there are any patients in the system
                    if (patientList.isEmpty()) {
                        System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("        ERROR: NO PATIENT HISTORY AVAILABLE.                ");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                        break;
                    }

                    System.out.println("\nPAST PRESCRIPTION HISTORY FOR ALL PATIENTS:");
                    System.out.println(" __________________________________________________________\n");

                    // Table header
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.printf("%-5s %-20s %-5s %-25s %-20s %-10s %-10s%n", 
                        "ID", "Patient Name", "Age", "Condition", "Medication", "Quantity", "Stock ATT");
                    System.out.println("----------------------------------------------------------------------------------------------------");

                    // Loop through each patient in the system
                    for (Patient patient : patientList) {
                        // Retrieve the associated prescription
                        Prescription presc = patient.getPrescription();

                        // Prepare the medication details for display
                        String medicationName = presc != null ? presc.getMedication().getName() : "No prescription";
                        String quantity = presc != null ? String.valueOf(presc.getQuantity()) : "-";
                        String stockAtTime = presc != null ? String.valueOf(presc.getStockAtPrescriptionTime()) : "-";

                        // Print patient and prescription details in a table row
                        System.out.printf("%-5d %-20s %-5d %-25s %-20s %-10s %-10s%n",
                            patient.getId(), patient.getName(), patient.getAge(),
                            patient.getCondition(), medicationName, quantity, stockAtTime);
                    }

                    System.out.println("-----------------------------------------------------------------------------------");
                    break;

                case 8:
                    // Exit
                    System.out.println("\n _________________________________________________________");
                    System.out.println("|                                                         |");
                    System.out.println("|            ~ EXITING THE SYSTEM. GOODBYE! ~             |");
                    System.out.println("|_________________________________________________________|\n");

                    input.close();
                    return;
            }
        }
    }
}
