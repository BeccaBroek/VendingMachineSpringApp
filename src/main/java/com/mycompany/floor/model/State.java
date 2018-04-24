/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.model;

/**
 *
 * @author Becca
 */
public class State {
    private String abbreviation;
    private String taxRate;
    
    public State(String abbreviation, String taxRate){
        this.abbreviation=abbreviation;
        this.taxRate=taxRate;
    }
    public String getTaxRate(){
        return taxRate;
    }
    
}
