package Pharmacy;

import java.util.Scanner;
import Medication.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        ArrayList<Medication> medicationList = new ArrayList<>(); 
        int menu;
        int autoIndex = 1;  

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
            System.out.println("|             >> 6. CHECK PATIENT MEDICAL RECORD          |");
            System.out.println("|             >> 7. EXIT                                  |");
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

            if (menu < 1 || menu > 7) { 
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

                        int index = autoIndex++;
                        
                        input.nextLine(); 
                        System.out.print("\n\t> MEDICATION NAME: ");
                        String name = input.nextLine();

                        System.out.print("\n\t> MEDICATION TYPE (e.g., Tablet, Syrup): ");
                        String type = input.nextLine();

                        System.out.print("\n\t> MANUFACTURER: ");
                        String manufacturer = input.nextLine();

                        System.out.print("\n\t> STOCK QUANTITY: ");
                        int stockQuantity = input.nextInt();

                        System.out.print("\n\t> PRICE(RM): ");
                        double price = input.nextDouble();

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

                            System.out.print("Enter the MEDICATION INDEX to update (or type 'back' to go back): ");
                            String inputIndex = input.next();

                            if (inputIndex.equalsIgnoreCase("back")) {
                                break;
                            }

                            try {
                                int updateIndex = Integer.parseInt(inputIndex);

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

                                    System.out.print("\nEnter new STOCK QUANTITY: ");
                                    int newStockQuantity = input.nextInt();

                                    System.out.print("Enter new PRICE(RM): ");
                                    double newPrice = input.nextDouble();

                                    // Update the medication details
                                    medicationToUpdate.setStockQuantity(newStockQuantity);
                                    medicationToUpdate.setPrice(newPrice);

                                    System.out.println("\n-----------------------------MEDICATION HAS BEEN UPDATED---------------------------------");
                                    System.out.println("___________________________________________________________________________________________\n");

                                    medicationToUpdate.displayDetailsAsTableHeader();
                                    medicationToUpdate.displayDetailsAsTableRow();

                                    System.out.println("___________________________________________________________________________________________\n");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID NUMBER.   ");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            }
                        }
                        
                        System.out.print("\nDo you want to update another medication? (yes or type no to go back to main menu): ");
                        String response = input.next();
                        while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no") )) {
                            System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("            WRONG INPUT. TRY AGAIN (yes or no).");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            response = input.next();
                        }
                        if (response.equalsIgnoreCase("no")) {
                            break;
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

                            System.out.print("Enter the MEDICATION INDEX to delete (or type 'back' to go back): ");
                            String inputIndex = input.next();

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
                                    autoIndex = medicationList.size() + 1;

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
                            } catch (NumberFormatException e) {
                                System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                System.out.println("     ERROR: INVALID INPUT. PLEASE ENTER A VALID NUMBER.   ");
                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                            }
                        }

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
                    break;
                    
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
                                return; 
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

                        input.nextLine(); 
                        System.out.print("\n\t> Enter new MEDICATION NAME (leave blank to keep current): ");
                        String newName = input.nextLine();
                        System.out.print("\n\t> Enter new MEDICATION TYPE (leave blank to keep current): ");
                        String newType = input.nextLine();
                        System.out.print("\n\t> Enter new MANUFACTURER (leave blank to keep current): ");
                        String newManufacturer = input.nextLine();
                        System.out.print("\n\t> Enter new STOCK QUANTITY (-1 to keep current): ");
                        int newStockQuantity = input.nextInt();
                        System.out.print("\n\t> Enter new PRICE(RM) (-1 to keep current): ");
                        double newPrice = input.nextDouble();

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
                    // Check patient medical record
                    System.out.println("Check Patient Medical Record selected.");
                    break;
                    
                case 7:
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
