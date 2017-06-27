/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.model;

import java.math.BigDecimal;

/**
 *
 * @author Becca
 */
public class Fruit {
    private String name;
    private BigDecimal price;
    private int quantity;
    
    public Fruit(String name, BigDecimal price, int quantity){
        this.quantity=quantity;
        this.name=name;
        this.price=price;
    }
    

    public String getName(){
        return this.name;
    }
    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantitly(int i){
        this.quantity=i;
    }

    public void removeOne(){
     this.quantity=this.quantity-1;
        
    }
    public BigDecimal getCost(){
        return this.price;
    }
    public void setQuantity(int number){
        this.quantity=number;
    }
}
