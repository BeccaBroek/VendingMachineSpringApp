/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.service;

import com.sg.vendingmachinespringapp.dao.VendingDao;
import com.sg.vendingmachinespringapp.model.Change;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Becca
 */
public class VendingServiceImpl implements VendingService{
     VendingDao VendingDao;
    @Inject
    public VendingServiceImpl(VendingDao VendingDao) {
        this.VendingDao = VendingDao;
    }
    @Override
    public void loadInventory () throws FileNotFoundException{
        VendingDao.load();
    }

    @Override
    public Collection getInventory() {
        return VendingDao.getAllFruit();
    }

    @Override
    public void checkMoneyRules(BigDecimal userMoney) throws InvalidMoneyInputException {
        //checks if money entered has two decimal places
        if (userMoney.scale() != 2) {
            throw new InvalidMoneyInputException(userMoney);
        } //checks that it is a positive value
        else if (userMoney.compareTo(new BigDecimal("0")) != 1) {
            throw new InvalidMoneyInputException(userMoney);
        } //checks that it is divisible by a nickel, user did not enter "pennies"
        else if (!userMoney.remainder(new BigDecimal(".05")).setScale(2).equals(new BigDecimal("0.00"))) {
            throw new InvalidMoneyInputException(userMoney);
        }
       
    }

    @Override
    public void checkInventory(String selection) throws InsufficientInventoryException {
        if(VendingDao.getFruit(selection).getQuantity() < 1){
            throw new InsufficientInventoryException();
        }
    }

    @Override
    public void checkUserEnoughMoney(BigDecimal userMoney, String selection) throws InsufficientFundsException {
        if ((VendingDao.getFruit(selection).getCost().compareTo(userMoney)) == (1)){
        throw new InsufficientFundsException(userMoney);
    }
    }

    private String determineSelection(int selectionNum) {
        String selectionName = " ";
        switch (selectionNum) {
            case 1:
                selectionName = "Apple";
                break;

            case 2:
                selectionName = "Watermelon";
                break;
            case 3:
                selectionName = "Blueberry";
                break;
            case 4:
                selectionName = "Pineapple";
                break;
        }
        return selectionName;
    }

    @Override
    public Change dispenseChange(BigDecimal userMoney) {
        Change change = new Change(userMoney);
        return change;
    }

    @Override
    public void removeItem(String selection) throws IOException {
        VendingDao.removeFruit(selection);
    }

    @Override
    public BigDecimal chargeAndReturnTotal(BigDecimal initialMoney, String selection) {
        
        BigDecimal newMoneyAmount= initialMoney.subtract(VendingDao.getFruit(selection).getCost());
        return newMoneyAmount;

    }
    
}