/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.service;

import java.math.BigDecimal;

/**
 *
 * @author Becca
 */
public class InsufficientInventoryException extends Exception {

    @Override
    public String getMessage() {
        return ("The vending machine is out of the item you requested. Please select another");
    }
    public String getName(){
        return"InsufficientInventoryException";
    }
}
