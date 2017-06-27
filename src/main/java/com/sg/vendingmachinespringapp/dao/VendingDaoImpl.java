/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringapp.dao;

import com.sg.vendingmachinespringapp.model.Fruit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Becca
 */
public class VendingDaoImpl implements VendingDao {
    
      HashMap<String, Fruit> fruitMap = new HashMap<>();

    public VendingDaoImpl() {
           try{load();}
           catch(FileNotFoundException e){
               
           }
//        Fruit apple=new Fruit("apple",new BigDecimal(".75"),0);
//        Fruit watermelon=new Fruit("watermelon",new BigDecimal("1.25"),10);
//        Fruit blueberry=new Fruit("blueberry",new BigDecimal(".10"),10);
//        Fruit pineapple=new Fruit("pineapple",new BigDecimal("1.00"),10);
//        fruitMap.put("apple",apple);
//        fruitMap.put("watermelon",watermelon);
//        fruitMap.put("blueberry",blueberry);
//        fruitMap.put("pineapple",pineapple);
    }

    @Override
    public void load() throws FileNotFoundException {
        Scanner loadScanner = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Becca\\Documents\\NetBeansProjects\\VendingMachineSpringApp\\inventory.txt")));
        String[] currentArray;
        while (loadScanner.hasNextLine()) {
            String currentLine = loadScanner.nextLine();
            currentArray = currentLine.split("::");
            String snackName = currentArray[0];
            fruitMap.put(snackName, new Fruit(currentArray[0], new BigDecimal(currentArray[2]), parseInt(currentArray[1])));
        }
    }

    @Override
    public Collection<Fruit> getAllFruit() {
        return fruitMap.values();
    }
    @Override
    public Fruit getFruit(String name) {
        return fruitMap.get(name);
    }

    @Override
    public void removeFruit(String selection) throws IOException {
        getFruit(selection).removeOne();
        save();
    }

    @Override
    public void save() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Becca\\Documents\\NetBeansProjects\\VendingMachineSpringApp\\inventory.txt"));
        Set<String> names = fruitMap.keySet();
        for (String type : names) {
            String name = (fruitMap.get(type)).getName();
            int quantity = (fruitMap.get(type)).getQuantity();
            BigDecimal price = (fruitMap.get(type)).getCost();

            out.println(name + "::" + quantity + "::" + price);

        }
        out.flush();
        out.close();
    }
    
}
