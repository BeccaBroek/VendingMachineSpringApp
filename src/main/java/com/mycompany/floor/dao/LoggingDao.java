/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Becca
 */
public class LoggingDao {
    public void write(String entry){
     
     PrintWriter out = null;
       
           try{ out = new PrintWriter(new FileWriter("auditFile.txt", true));
           }catch (IOException e){
               System.out.println("Could not write audit");
           }
                  
        
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    
}
}
