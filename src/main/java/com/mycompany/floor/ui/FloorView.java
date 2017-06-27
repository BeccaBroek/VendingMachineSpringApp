/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.ui;

import com.mycompany.floor.model.Order;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Becca
 */
public class FloorView {

    UserIO io;

    public FloorView(UserIO io) {
        this.io = io;
    }
    public int getMode(){
        return io.readInt("Would you like to operate in training or production mode?\n1 . Training\n2 . Production",1,2);
    }
    public void displayMainMenu() {
        io.print("................................");
        io.print("1 . Display all orders by Date\n2 . Add an order\n3 . Edit an order\n4 . Remove an order\n5 . Save\n6 . Exit");
        io.print("................................");
    }

    public void displayMessage(String message) {
        io.print(message);
    }

    public int getUserChoice() {
        return io.readInt("Pick a menu choice.", 1, 6);
    }

    public String askForDate() {
        return io.readString("Please enter Date in MMDDYYYY");
    }

    public void displayOrders(HashMap<String, Order> orders) {
        Set<String> orderNumbers = orders.keySet();
        String[] types = {"Order Number: ", "\nName: ", "\nState: ", "\nTax Rate :", "\nProduct Type :", "\nArea: ", "\nCost Per Square Foot: ", "\nLabor cost per square foot: $", "\nMaterial Cost: $", "\nLabor Cost: $", "\nTax: S", "\nTotal: S"};
        for (String number : orderNumbers) {
            String[] info = orders.get(number).getOrderInfo();
            String output = "";
            for (int i = 0; i < types.length; i++) {
                output = output + types[i] + info[i];
            }
            io.print("------------------------------------------");
            io.print(output);

        }
    }

    public void printError(String message) {
        io.print(message);
    }

    public String getOrderInfo(String aspect) {
        return io.readString(aspect);
    }

    public boolean confirm(String customerName, String customerState, String productType, String area) {
        io.print("Order Info:\nCustomer Name: " + customerName + "\nState: " + customerState + "\nProduct Type: " + productType + "\nArea: " + area);
        io.print("--------------------------------");
        int yesOrNo = io.readInt("Would you like to confirm?:\n1.Yes\n2.No", 1, 2);
        return yesOrNo == 1;
    }
}
