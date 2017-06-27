/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import com.mycompany.floor.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Becca
 */
public class FloorProductDaoImpl implements FloorProductDao{
    HashMap<String, Product> productsMap = new HashMap<>();
    @Override
    public void loadProducts() throws FileNotFoundException{
        Scanner loadScanner = new Scanner(new BufferedReader(new FileReader("Products.txt")));
        String[] currentArray;
        while (loadScanner.hasNextLine()) {
            String currentLine = loadScanner.nextLine();
            currentArray = currentLine.split(",");
            String productType = currentArray[0];
            productsMap.put(productType, new Product(currentArray[0], currentArray[1], currentArray[2]));
        }
    }
    @Override
    public Product getProduct(String productType) throws FileNotFoundException{
        loadProducts();
        return productsMap.get(productType);
    }
    @Override
    public Set<String> getProducts() throws FileNotFoundException{
        loadProducts();
        return productsMap.keySet();
    }
}

