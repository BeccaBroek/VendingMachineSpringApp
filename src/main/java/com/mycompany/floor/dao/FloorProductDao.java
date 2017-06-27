/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import com.mycompany.floor.model.Product;
import java.io.FileNotFoundException;
import java.util.Set;

/**
 *
 * @author Becca
 */
public interface FloorProductDao {
    public void loadProducts() throws FileNotFoundException;
    public Product getProduct(String productType) throws FileNotFoundException;
    public Set<String> getProducts()throws FileNotFoundException;
}

