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
public class InvalidMoneyInputException extends Exception {
    private BigDecimal amount;
   public InvalidMoneyInputException(BigDecimal amount)
   {
      this.amount = amount;
   } 
   public String getMessage()
   {
      return ("Please enter a valid amount. Must be in $ _.__ format, do not enter any pennies. "+amount+" is not valid.");
   }
}
