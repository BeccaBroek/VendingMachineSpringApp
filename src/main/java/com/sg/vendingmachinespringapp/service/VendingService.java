/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.service;

import com.sg.vendingmachinespringapp.model.Change;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Becca
 */
public interface VendingService {
    
    
    void loadInventory () throws FileNotFoundException;

    public Collection getInventory();

    public void checkMoneyRules(BigDecimal userMoney) throws InvalidMoneyInputException ;

    public void checkInventory(String selection) throws InsufficientInventoryException ;

    public void checkUserEnoughMoney(BigDecimal userMoney, String selection) throws InsufficientFundsException ;

    

    public Change dispenseChange(BigDecimal userMoney) ;

    public void removeItem(String selection) throws IOException ;

    public BigDecimal chargeAndReturnTotal(BigDecimal initialMoney, String selection) ;
    
}