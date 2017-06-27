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
public class InsufficientFundsException extends Exception {
   private BigDecimal amount;
   public InsufficientFundsException(BigDecimal amount)
   {
      this.amount = amount;
   } 
   @Override
   public String getMessage()
   {
      return ("You do not have enough money inserted. You only put in $"+amount);
   }
   public String getName(){
       return "InsufficientFundsException";
   }
}
