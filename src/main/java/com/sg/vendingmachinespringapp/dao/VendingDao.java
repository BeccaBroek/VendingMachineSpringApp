/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.dao;

import com.sg.vendingmachinespringapp.model.Fruit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Becca
 */
public interface VendingDao {
    void load()throws FileNotFoundException;
    public void save() throws IOException;
    public void removeFruit(String selection) throws IOException;
    Collection<Fruit> getAllFruit();
    Fruit getFruit(String name);
    
    
}