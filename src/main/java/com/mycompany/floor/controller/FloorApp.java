/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Becca
 */
public class FloorApp {
    // Add test for DAO, AOP, streams

    public static void main(String[] args) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String iconConfigPath = rootPath + "configuration.xml";
        Properties iconProps = new Properties();
        try {
            iconProps.loadFromXML(new FileInputStream(iconConfigPath));
        } catch (FileNotFoundException e) {
            System.out.println("Could not load configuration file");
            } catch (IOException n) {
            System.out.println("Could not load configuration file");
            }
        String context = iconProps.getProperty("mode");    
        
            ApplicationContext ctx
                    = new ClassPathXmlApplicationContext(context);
            FloorController controller
                    = ctx.getBean("Controller", FloorController.class);
            controller.execute();
        }

    }
