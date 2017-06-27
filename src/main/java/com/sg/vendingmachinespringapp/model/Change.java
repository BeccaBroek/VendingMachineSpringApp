/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Becca
 */
public class Change {
    private BigDecimal totalMoney;
    private int quarters;
    private int dimes;
    private int nickels;
    final private BigDecimal QUARTER_VALUE = new BigDecimal(".25");
    final private BigDecimal DIME_VALUE = new BigDecimal(".10");
    final private BigDecimal NICKEL_VALUE = new BigDecimal(".05");
    
    public Change(BigDecimal purchaseAmount) {
    this.totalMoney=purchaseAmount;
        quarters = totalMoney.divide(QUARTER_VALUE).setScale(0, RoundingMode.DOWN).intValue();
        totalMoney = totalMoney.subtract((totalMoney.divide(QUARTER_VALUE).setScale(0, RoundingMode.DOWN)).multiply(QUARTER_VALUE));
        if (totalMoney.equals(new BigDecimal("0"))) {
            dimes = 0;
        } else {
            dimes = totalMoney.divide(DIME_VALUE).setScale(0, RoundingMode.DOWN).intValue();
            totalMoney = totalMoney.subtract((totalMoney.divide(DIME_VALUE).setScale(0, RoundingMode.DOWN)).multiply(DIME_VALUE));
        }
        if (totalMoney.equals(new BigDecimal("0"))) {
            nickels = 0;
        } else {
            nickels = totalMoney.divide(NICKEL_VALUE).setScale(0, RoundingMode.DOWN).intValue();
            totalMoney = totalMoney.subtract((totalMoney.divide(NICKEL_VALUE).setScale(0, RoundingMode.DOWN)).multiply(NICKEL_VALUE));
        }
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }
    
}
