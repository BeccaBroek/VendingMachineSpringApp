/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;



/**
 *
 * @author Becca
 */

import com.mycompany.floor.model.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


/**
 *
 * @author Becca
 */
public class FloorOrderDaoImplTrain implements FloorOrderDao {

    HashMap<String, HashMap<String, Order>> memory = new HashMap<>();

    @Override
    public void load() throws FileNotFoundException {
        
           
    }

    @Override
    public HashMap<String, Order> getByDate(String date) {
        return memory.get(date);
    }

    @Override
    public HashMap<String, HashMap<String, Order>> getAll() {
        return memory;
    }

    @Override
    public void add(Order order, String date, String orderNumber) {
        if(!memory.keySet().contains(date)){
            HashMap<String,Order> orderMap= new HashMap<>();
            orderMap.put(orderNumber, order);
            memory.put(date, orderMap);
        }
        else{memory.get(date).put(orderNumber, order);}
    }
        @Override
    public void remove(String date, String orderNumber)throws IOException{
        memory.get(date).remove(orderNumber);
    }

    @Override
    public void save() throws IOException {
       
}
    
}
