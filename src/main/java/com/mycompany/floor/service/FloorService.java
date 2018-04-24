/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.service;

import com.mycompany.floor.model.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Becca
 */
public interface FloorService {
    public void load() throws FileNotFoundException;
    public HashMap<String,Order> getByDate(String date) throws FileNotFoundException;
    public void addOrder(String customerName, String customerState, String productType, String area) throws FileNotFoundException;
    public void check(String type, String value) throws InvalidInputException, FileNotFoundException;
    public void checkName(String name) throws InvalidInputException;
    public void checkState(String State) throws InvalidInputException, FileNotFoundException;
    public void checkProductType(String productType) throws InvalidInputException, FileNotFoundException;
    public void checkArea(String area) throws InvalidInputException;
    public void save() throws IOException;
    public void checkForOrder(String orderDate, String orderNumber) throws FileNotFoundException;
    public Order getOrder(String orderNumber, String date);
    public void editOrder(String orderNumber, String date, String name, String state, String product, String area) throws IOException;
    public void removeOrder(String date, String orderNumber) throws IOException;
    
    
}
