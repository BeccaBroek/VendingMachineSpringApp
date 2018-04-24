/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.model;

import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 *
 * @author Becca
 */
public class Order {
    private String number;
    private String customerName;
    private String customerState;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;

    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    public Order(String number, String customerName, String customerState, String taxRate, String productType, String area, String costPerSqFt, String laborCostPerSqFt) {
        this.number = number;
        this.customerName = customerName;
        this.customerState = customerState;
        this.taxRate = new BigDecimal(taxRate);
        this.productType = productType;
        this.area = new BigDecimal(area);
        this.costPerSqFt = new BigDecimal(costPerSqFt);
        this.laborCostPerSqFt = new BigDecimal(laborCostPerSqFt);
    }
    public String getMaterialCost() {
        materialCost = costPerSqFt.multiply(area);
        return materialCost.setScale(2, ROUND_HALF_DOWN).toString();
    }
    public String getLaborCost() {
        laborCost = laborCostPerSqFt.multiply(area);
        return laborCost.setScale(2, ROUND_HALF_DOWN).toString();
    }
    public String getTax() {
        BigDecimal costWithoutTax = materialCost.add(laborCost);
        tax = (taxRate.divide(new BigDecimal("100")).multiply(costWithoutTax));
        return tax.setScale(2, ROUND_HALF_DOWN).toString();
    }
    public String getTotal() {
        total = materialCost.add(laborCost).add(tax);
        return total.setScale(2, ROUND_HALF_DOWN).toString();
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public String getNumber() {
        return number;
    }
    public String getCustomerState() {
        return customerState;
    }
    public String getProductType() {
        return productType;
    }
    public String getArea() {
        return area.toString();
    }
    public String getTaxRate() {
        return taxRate.toString();
    }
    public String getCostPerSqFt() {
        return costPerSqFt.toString();
    }
    public String getLaborCostPerSqFt() {
        return laborCostPerSqFt.toString();
    }
    public String[] getOrderInfo() {
        String[] orderInfo = {number, customerName, customerState, String.valueOf(taxRate), productType, String.valueOf(area), String.valueOf(costPerSqFt), String.valueOf(laborCostPerSqFt), String.valueOf(getMaterialCost()), String.valueOf(getLaborCost()), String.valueOf(getTax()), String.valueOf(getTotal())};
        return orderInfo;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public void setArea(String area) {
        this.area = new BigDecimal(area);
    }
}
