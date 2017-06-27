/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.service;

/**
 *
 * @author Becca
 */
public class InvalidInputException extends Exception {
    private String type;
    public InvalidInputException(String inputType){
        this.type=inputType;
    }
    @Override
    public String getMessage(){
        return "Invalid input, please enter the "+type+" again.";
        
    }
}
