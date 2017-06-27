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
public interface FloorOrderDao {
    public void load() throws FileNotFoundException;
    public HashMap<String, Order> getByDate(String date);
    public void add(Order order, String date, String orderNumber);
    public HashMap<String, HashMap<String,Order>> getAll();
    public void save() throws IOException;
    public void remove(String date, String orderNum)throws IOException;
}
