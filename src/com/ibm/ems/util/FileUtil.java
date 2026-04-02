package com.ibm.ems.util;

import com.ibm.ems.model.*;

import java.io.*;
import java.util.*;

public class FileUtil {

    private static final String FILE_NAME = "employees.txt";

   
    public static void saveToFile(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Employee e : employees) {
                bw.write(e.toString()); 
                bw.newLine();
            }
            
            

            System.out.println("Employees saved successfully.");

        } catch (IOException e) {
            System.out.println("Error while saving file.");
        }
    }

    
    public static List<Employee> loadFromFile() {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double salary = Double.parseDouble(data[2]);
                String type = data[3];

                // Recreate object based on type
                if (type.equals("PermanentEmployee")) {
                    employees.add(new PermanentEmployee(id, name, salary));
                } else if (type.equals("ContractEmployee")) {
                    employees.add(new ContractEmployee(id, name, salary));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }

        return employees;
    }
}