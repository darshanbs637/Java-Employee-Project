package com.ibm.ems.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cli {
    private static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));;
    public static void startCli(){
        int userOption;
        while (true) { 
            printOption();
            try {
                String line = reader.readLine();
                userOption=Integer.parseInt(line);
                switch (userOption) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                    case 4 -> {
                    }
                    case 5 -> {
                    }
                    case 6 -> {
                    }
                    case 7 -> {
                    }
                    case 8 -> {
                    }
                    default -> {
                        System.out.println("Enter Valid option");
                    }
                }

            } catch (IOException ex) {
                System.err.println("IO ERROR OCCURED");
                System.exit(1);
            } catch (NumberFormatException e){
                System.err.println("INVALID INPUT");
            }
        }
        
    }
    private static void printOption(){
        System.err.println("----------\nIBM-EMS-APP\n----------");
        System.out.println("1) Add Employee");
        System.out.println("2) View Employees");
        System.out.println("3) Update Employee");
        System.out.println("4) Delete Employee");
        System.out.println("5) Search Employee");
        System.out.println("6) Process Salary");
        System.out.println("7) Save Data");
        System.out.println("8) Exit");
    }
    
}
