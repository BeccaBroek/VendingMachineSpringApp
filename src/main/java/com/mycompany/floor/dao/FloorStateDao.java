/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import java.io.FileNotFoundException;
import java.util.Set;

/**
 *
 * @author Becca
 */
public interface FloorStateDao {
    public void loadTaxes() throws FileNotFoundException;
    public String getTax(String state) throws FileNotFoundException;
    public Set<String> getStates() throws FileNotFoundException;

   
}
