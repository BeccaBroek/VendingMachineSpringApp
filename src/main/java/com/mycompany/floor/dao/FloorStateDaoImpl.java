/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import com.mycompany.floor.model.State;
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
public class FloorStateDaoImpl implements FloorStateDao {
HashMap<String, State> stateTaxesMap = new HashMap <>();
@Override
public void loadTaxes() throws FileNotFoundException{
    Scanner loadScanner = new Scanner(new BufferedReader(new FileReader("StatesAndTaxes.txt")));
        String[] currentArray;
        while (loadScanner.hasNextLine()) {
            String currentLine = loadScanner.nextLine();
            currentArray = currentLine.split(",");
            String stateName = currentArray[0];
            stateTaxesMap.put(stateName, new State(stateName, currentArray[1]));

        }
}
@Override
public String getTax(String state) throws FileNotFoundException{
    loadTaxes();
    return stateTaxesMap.get(state).getTaxRate();
}
@Override
public Set<String> getStates() throws FileNotFoundException {
    loadTaxes();
    return stateTaxesMap.keySet();
}
    
}
