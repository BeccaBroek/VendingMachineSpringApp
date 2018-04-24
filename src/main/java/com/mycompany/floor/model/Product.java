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
public class Product {
    private String productType;
    private String costPerSqFt;
    private String laborCostPerSqFt;
    
    public Product(String productType, String costPerSqFt, String laborCostPerSqFt){
        this.productType=productType;
        this.costPerSqFt=costPerSqFt;
        this.laborCostPerSqFt=laborCostPerSqFt;
    }

    public String getProductType() {
        return productType;
    }

    public String getCostPerSqFt() {
        return costPerSqFt;
    }

    public String getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }
}
