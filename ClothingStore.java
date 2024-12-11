package com.example.project210;
/*
2.	Develop a class called ClothingStore that will use array fields to store the username and password,
 names of the items, and their unit costs. It will also contain a field for tax with a rate of 8%.
You should create three arrays namely a 2-dimensional array for the username and password with
entries username, password, and “Macys” and “1 Herald Square” respectively. A regular array for
the items’ names (you create any 4 types)  and another array for the items’ prices (You create any 4 prices).
In addition, the class will contain 7 methods: one to return the username, another to return the password,
another to return a price given an item name, another to return the names of the items, another to calculate
the cost, another to calculate the taxes, and another to calculate the total cost.
*/
public class ClothingStore {
    private String[][] login = {{"Username", "Macy"}, {"Password", "1 Herald Square"}};
    private String[] Itemname = {"Sweater", "Jeans", "Coat", "Jordans", "Watch"};
    private double[] ItemPrice = {19.99, 21.99, 34.99, 29.99, 44.99};
    private double tax = 0.08;

    //Method to get username
    public String getusername() {
        return login[0][1];
    }

    //Method to get password
    public String getpassword() {
        return login[1][1];
    }

    // Method to get all item names
    public String[] getItemNames() {
        return Itemname; // Return the array of item names
    }

    //Method to get itemPrice
    public double getItemPrice(String itemName) {
        for (int i = 0; i < Itemname.length; i++) {
            if (Itemname[i].equalsIgnoreCase(itemName)) {

                return ItemPrice[i];
            }
        }
        return -1;
    }

    // Method to calculate the total cost (cost + tax)
    public double calculateCost(String itemName, int quantity) {
        double price = getItemPrice(itemName);
        if (price == -1) {
            System.out.println("Item not found.");
            return 0;
        }
        return price * quantity;
    }

    // Method to Calculate the tax based on the pre-cost
    public double calculateTaxes(double preCost) {
        return preCost * tax; // Calculate tax (preCost * tax rate)
    }

    // Calculate the total cost including tax
    public double totalCost(double preCost) {
        double taxAmount = calculateTaxes(preCost); // Calculate tax
        return preCost + taxAmount; // Return total cost (preCost + tax)
    }
}