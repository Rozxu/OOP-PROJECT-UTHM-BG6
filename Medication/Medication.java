package Medication;

public class Medication {
    private int index;
    private String name;
    private String type;
    private String manufacturer;
    private int stockQuantity;
    private double price;

    // Constructor
    public Medication(int index, String name, String type, String manufacturer, int stockQuantity, double price) {
        this.index = index;
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    // Method to update stock
    public void updateStock(int additionalStock) {
        if (additionalStock < 0 && Math.abs(additionalStock) > this.stockQuantity) {
            System.out.println("Error: Not enough stock to remove.");
        } else {
            this.stockQuantity += additionalStock;
            System.out.println("Stock updated successfully.");
        }
    }

    // Method to update stock based on prescribed quantity
    public void updatePresStock(int prescribedQuantity) {
        if (prescribedQuantity <= this.stockQuantity) {
            this.stockQuantity -= prescribedQuantity;  // Deduct prescribed quantity from available stock
            System.out.println("Stock updated successfully. Remaining stock for " + this.name + ": " + this.stockQuantity);
        } else {
            System.out.println("Error: Insufficient stock for medication: " + this.name);
        }
    }

    // Display Details in Table Format
    public void displayDetailsAsTableHeader() {
        System.out.printf("%-10s %-20s %-15s %-20s %-15s %-10s%n", "Index", "Name", "Type", "Manufacturer", "Stock Quantity", "Price(RM)");
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    public void displayDetailsAsTableRow() {
        System.out.printf("%-10d %-20s %-15s %-20s %-15d %-10.2f%n", 
            index, name, type, manufacturer, stockQuantity, price);
    }
}

