/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import com.mycompany.floor.model.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Becca
 */
public class FloorOrderDaoStubImpl implements FloorOrderDao{
    HashMap<String, HashMap<String,Order>> stubMemory= new HashMap<>();
    public FloorOrderDaoStubImpl(){
        String date="06092017";
        Order testOrder= new Order("1","Wise","OH","6.25","Wood","100.00","5.15","4.75");
        Order testOrderTwo= new Order("2","Mike","OH","6.25","Wood","100.00","5.15","4.75");
        HashMap<String,Order> testMap= new HashMap<>(); 
        testMap.put(testOrder.getNumber(), testOrder);
        testMap.put(testOrderTwo.getNumber(), testOrderTwo);
        stubMemory.put(date, testMap);
    }
    @Override
    public void load() throws FileNotFoundException {
    }

    @Override
    public HashMap<String, Order> getByDate(String date) {
        return stubMemory.get(date);
    }

    @Override
    public void add(Order order, String date, String orderNumber) {
        if(stubMemory.containsKey(date)){
            stubMemory.get(date).clear();
        }
        HashMap<String,Order> newMap= new HashMap<>();
        newMap.put(orderNumber,order);
        stubMemory.put(date, newMap);
    }

    @Override
    public HashMap<String, HashMap<String, Order>> getAll() {
        return stubMemory;
    }

    @Override
    public void save() throws IOException {
    }

    @Override
    public void remove(String date, String orderNum) throws IOException {
        if(stubMemory.containsKey(date)){
            if(stubMemory.get(date).containsKey(orderNum)){
                return;
            }
            else{throw new IOException();}
        }
        else{throw new IOException();}
    }
    
}
