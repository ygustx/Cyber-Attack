package javaapplication1;

public class Product {
    private String name;
    private String type;
    private int quantity;

    public Product(String name, String type, int quantity) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
