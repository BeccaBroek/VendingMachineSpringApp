/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.controller;

import com.mycompany.floor.model.Order;
import com.mycompany.floor.service.FloorService;
import com.mycompany.floor.service.InvalidInputException;
import com.mycompany.floor.ui.FloorView;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Becca
 */
public class FloorController {

    FloorService service;
    FloorView view;
    boolean keepRunning = true;

    public FloorController(FloorView view, FloorService service) {
        this.service = service;
        this.view = view;
    }

    public void execute() {
        try {
            service.load();
        } catch (FileNotFoundException e) {
            view.printError("Could not load file");
        }
        do {
            view.displayMainMenu();
            int choice = view.getUserChoice();
            switch (choice) {
                case 1:
                    displayOrdersByDate();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    try {
                        service.save();
                        view.displayMessage("Your changes have been saved.");
                        break;
                    } catch (IOException e) {
                        view.printError("Unable to save");
                    }
                    try {
                        service.load();
                    } catch (FileNotFoundException e) {
                        view.printError("Unable to load");
                    }
                case 6:
                    view.displayMessage("Thank you, Goodbye!");
                    keepRunning = false;
            }
        } while (keepRunning == true);
    }

    private void displayOrdersByDate() {
        String date = view.askForDate();
        try {
            view.displayOrders(service.getByDate(date));
        } catch (FileNotFoundException | NullPointerException e) {
            view.printError("There are no orders for that date.");
        }
    }

    private void addOrder() {
        String customerName = getNewInfo("Name");
        String customerState = getNewInfo("State");
        String productType = getNewInfo("Product type");
        String area = getNewInfo("Area");
        Boolean submit = view.confirm(customerName, customerState, productType, area);
        if (submit == true) {
            try {
                service.addOrder(customerName, customerState, productType, area);
                view.displayMessage("Your order for " + customerName + " was successfully added");
            } catch (FileNotFoundException e) {
                view.printError("Unable to add order. could not load order file.");
            }
        } else {
            view.displayMessage("Your order was discarded");
        }
    }

    private void editOrder() {
        boolean keepAsking;
        String orderNumber = "";
        String date = "";
        do {
            try {
                date = view.askForDate();
                orderNumber = view.getOrderInfo("Order number:");
                service.checkForOrder(date, orderNumber);
                keepAsking = false;
            } catch (FileNotFoundException e) {
                view.printError("That order does not exist.");
                keepAsking = true;
            }
        } while (keepAsking == true);
        Order thisOrder = service.getOrder(orderNumber, date);
        view.displayMessage("Enter new customer Name (" + thisOrder.getCustomerName() + "):");
        String newCustomerName = getNewInfo("Name");
        view.displayMessage("Enter new customer State (" + thisOrder.getCustomerState() + "):");
        String newCustomerState = getNewInfo("State");
        view.displayMessage("Enter new product type (" + thisOrder.getProductType() + "):");
        String newProductType = getNewInfo("product type");
        view.displayMessage("Enter new area (" + thisOrder.getArea() + "):");
        String newArea = getNewInfo("area");

        try {
            service.editOrder(orderNumber, date, newCustomerName, newCustomerState, newProductType, newArea);
        } catch (IOException e) {
            view.printError("unable to save edit.");
        }
    }

    private void removeOrder() {
        Boolean keepAsking;
        String date = "";
        String orderNumber = "";
        do {
            try {
                date = view.askForDate();
                orderNumber = view.getOrderInfo("Order number:");
                service.checkForOrder(date, orderNumber);
                keepAsking = false;
            } catch (FileNotFoundException e) {
                view.printError("That order does not exist.");
                keepAsking = true;
            }
        } while (keepAsking == true);
        Order orderToDelete = service.getOrder(orderNumber, date);
        boolean confirm = view.confirm(orderToDelete.getCustomerName(), orderToDelete.getCustomerState(), orderToDelete.getProductType(), orderToDelete.getArea());
        if (confirm == true) {
            try {
                service.removeOrder(date, orderNumber);
                view.displayMessage("Your order was removed");
            } catch (IOException e) {
                view.printError("Unable to remove order.");
            }
        } else {
            view.displayMessage("The order was not removed.");
        }
    }

    private String getNewInfo(String type) {
        boolean keepAsking = false;
        String value = "";
        do {
            try {
                keepAsking = false;
                value = view.getOrderInfo(type);
                service.check(type, value);
            } catch (InvalidInputException i) {
                view.printError(i.getMessage());
                keepAsking = true;
            } catch (FileNotFoundException k) {
                view.printError("unable to load from file");
            }
        } while (keepAsking == true);
        return value;
    }
}
