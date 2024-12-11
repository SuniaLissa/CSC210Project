package com.example.project210;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Login extends Application {
    // Create Fields
    private TextField Username;
    private TextField Password;
    private Label Result;

    // Fields for item selection and quantity
    private TextField ItemField;
    private TextField QuantityField;
    private Label PreCostLabel;
    private Label TaxLabel;
    private Label TotalCostLabel;

    // Clothing store reference (instance variable for the entire class)
    ClothingStore c;

    public static void main(String[] args) {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize the ClothingStore object once here, not inside Enter class
        c = new ClothingStore();

        // Create gridpane object
        GridPane gridpane = new GridPane();

        // Create Label object
        Label label1 = new Label("Username ");
        Label label2 = new Label("Password ");

        // Initialize the label
        Result = new Label();

        // Create Text field components
        Username = new TextField();
        Password = new TextField();

        // Create button component
        Button b = new Button("Login");

        // Register the event handler.
        b.setOnAction(new Enter(primaryStage));

        // Add controls to gridpane
        gridpane.add(label1, 0, 0);
        gridpane.add(label2, 0, 1);
        gridpane.add(Username, 1, 0);
        gridpane.add(Password, 1, 1);
        gridpane.add(b, 1, 2);
        gridpane.add(Result, 1, 3);

        // Create the Scene and add it to the Stage
        Scene scene = new Scene(gridpane);

        // Add the Scene to the Stage.
        primaryStage.setScene(scene);

        // Set the stage title and show the window.
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    // Method to show the Clothing Store page after successful login
    private void showClothingStorePage(Stage primaryStage) {
        // Create a new gridpane object for the Clothing Store page
        GridPane gridpane = new GridPane();

        // Create labels
        Label itemHeader = new Label("Item");
        Label priceHeader = new Label("Price");

        // Add controls to gridpane
        gridpane.add(itemHeader, 0, 0);
        gridpane.add(priceHeader, 1, 0);


        // Display all items and their prices
        String[] itemNames = c.getItemNames(); // Get all item names
        for (int i = 0; i < itemNames.length; i++) {
            String itemName = itemNames[i];
            double price = c.getItemPrice(itemName); // Get the price for each item
            gridpane.add(new Label(itemName), 0, i + 1); // Add item name to grid
            gridpane.add(new Label("$" + price), 1, i + 1); // Add price to grid
        }

        // Create fields for the user to select an item and enter quantity
        Label itemLabel = new Label("Enter Item:");
        ItemField = new TextField();

        Label quantityLabel = new Label("Enter Quantity:");
        QuantityField = new TextField();

        // Labels for displaying the results
        PreCostLabel = new Label("Pre-Cost: $0.00");
        TaxLabel = new Label("Tax: $0.00");
        TotalCostLabel = new Label("Total Cost: $0.00");

        // Button to calculate the cost
        Button calculateButton = new Button("Calculate Cost");

        // Event handler for the calculate button
        calculateButton.setOnAction(e -> handleCalculateCost());

        // Add controls to gridpane for item selection and quantity
        gridpane.add(itemLabel, 0, itemNames.length + 1);
        gridpane.add(ItemField, 1, itemNames.length + 1);
        gridpane.add(quantityLabel, 0, itemNames.length + 2);
        gridpane.add(QuantityField, 1, itemNames.length + 2);
        gridpane.add(calculateButton, 1, itemNames.length + 3);
        gridpane.add(PreCostLabel, 1, itemNames.length + 4);
        gridpane.add(TaxLabel, 1, itemNames.length + 5);
        gridpane.add(TotalCostLabel, 1, itemNames.length + 6);

        // Create and set a new scene for the clothing store page
        Scene clothingStoreScene = new Scene(gridpane);

        // Change scene to the clothing store page
        primaryStage.setScene(clothingStoreScene);
        primaryStage.setTitle("Clothing Store");
    }

    private void handleCalculateCost() {
        //Get text from ItemField and QuantityField
        String itemName = ItemField.getText();
        String quantityText = QuantityField.getText();

        // Check if the user has entered both an item and a quantity
        if (itemName.isEmpty() || quantityText.isEmpty()) {
            PreCostLabel.setText("Please enter both item and quantity.");
            TaxLabel.setText("");
            TotalCostLabel.setText("");
            return;
        }

        // Parse the valid quantity
        int quantity = Integer.parseInt(quantityText);

        // Get the price for the item
        double price = c.getItemPrice(itemName);
        if (price == -1) {
            PreCostLabel.setText("Item not found.");
            TaxLabel.setText("");
            TotalCostLabel.setText("");
            return;
        }

        // Calculate pre-cost, tax, and total cost
        double preCost = c.calculateCost(itemName, quantity);
        double tax = c.calculateTaxes(preCost);
        double totalCost = c.totalCost(preCost);

        // Display the results
        PreCostLabel.setText(String.format("Pre-Cost: $%.2f", preCost));
        TaxLabel.setText(String.format("Tax: $%.2f", tax));
        TotalCostLabel.setText(String.format("Total Cost: $%.2f", totalCost));
    }


    // Inner class to handle the login action
    class Enter implements EventHandler<ActionEvent> {
        private Stage primaryStage;

        // Constructor to pass primaryStage
        public Enter(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        @Override
        public void handle(ActionEvent event) {
            // Get Username and Password from text fields
            String usernameInput = Username.getText();
            String passwordInput = Password.getText();

            // Validate the login
            if (c.getusername().equals(usernameInput) && c.getpassword().equals(passwordInput)) {
                Result.setText("Login Successful");

                // Show the Clothing Store page after successful login
                showClothingStorePage(primaryStage);
            } else {
                Result.setText("Incorrect Login");
            }
        }
    }
}
