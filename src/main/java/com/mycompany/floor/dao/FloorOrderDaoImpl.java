/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import com.mycompany.floor.model.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Becca
 */
public class FloorOrderDaoImpl implements FloorOrderDao {

    HashMap<String, HashMap<String, Order>> memory = new HashMap<>();

    @Override
    public void load() throws FileNotFoundException {
        File file = new File("..\\Floor\\");
        File[] listOfFiles = file.listFiles();
        for (File name : listOfFiles) {
            if (name.getName().contains("Order_")) {
                Scanner loadScanner = new Scanner(new BufferedReader(new FileReader(name.getName())));
                String[] currentArray;
                while (loadScanner.hasNextLine()) {
                    String currentLine = loadScanner.nextLine();
                    currentArray = currentLine.split(",");
                    String orderNumber = currentArray[0];
                    String orderDate = name.getName().substring(6, 14);
                    Order newOrder = new Order(currentArray[0], currentArray[1], currentArray[2], currentArray[3], currentArray[4], currentArray[5], currentArray[6], currentArray[7]);
                    HashMap<String, Order> newMap = new HashMap<>();
                    newMap.put(orderNumber, newOrder);
                    
                    try{memory.get(orderDate).put(orderNumber,newOrder);}
                    catch(NullPointerException e){
                        memory.put(orderDate, newMap);
                    }
                }
            }

        }
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
        Set<String> orderDates = memory.keySet();
        for (String date : orderDates) {
            String fileName = ("Order_" + date + ".txt");
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            Set<String> orderNumbers = memory.get(date).keySet();
            for (String number : orderNumbers) {
                Order currentOrder = memory.get(date).get(number);
                String[] orderInfo = currentOrder.getOrderInfo();
                String infoOut = "";
                for (String orderInfo1 : orderInfo) {
                    infoOut = infoOut + orderInfo1 + ",";
                }
                out.println(infoOut);

            }
            out.flush();
             out.close();
        }
    }
}
