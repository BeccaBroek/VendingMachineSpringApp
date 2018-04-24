/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.ui;

/**
 *
 * @author Becca
 */
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.util.Scanner;

/**
 *
 * @author Becca
 */
public class UserIOImpl implements UserIO {

    Scanner myScanner = new Scanner(System.in);

    @Override

    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double myDouble = parseDouble(myScanner.nextLine());
        return myDouble;

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double number=max+1;
        System.out.println(prompt);
        do {
            System.out.println("Please enter a number between "+min+" and "+max);
            number = parseDouble(myScanner.nextLine());

        } while (number < min || number > max);
        return number;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float myFloat = parseFloat(myScanner.nextLine());
        return myFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float number=max+1;
        System.out.println(prompt);
        do {
            System.out.println("Please enter a number between "+min+" and "+max);
            number = parseFloat(myScanner.nextLine());

        } while (number < min || number > max);
        return number;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int myInt = parseInt(myScanner.nextLine());
        return myInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int number=max+1;
        System.out.println(prompt);
        do {
            System.out.println("Please enter a number between "+min+" and "+max);
            number = parseInt(myScanner.nextLine());

        } while (number < min || number > max);
        return number;

    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long myLong = parseLong(myScanner.nextLine());
        return myLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long number=max+1;
        System.out.println(prompt);
        do {
            System.out.println("Please enter a number between "+min+" and "+max);
            number = parseLong(myScanner.nextLine());

        } while (number < min || number > max);
        return number;
    }

    public String readString(String prompt) {
        System.out.println(prompt);
        String myString = myScanner.nextLine();
        return myString;
    }

}

