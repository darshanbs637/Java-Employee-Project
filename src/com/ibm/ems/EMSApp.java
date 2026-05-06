package com.ibm.ems;

import com.ibm.ems.model.*;
import com.ibm.ems.service.EmployeeService;
import com.ibm.ems.util.FileUtil;
import com.ibm.ems.exception.EmployeeNotFoundException;

import java.util.*;

public class EMSApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        // Load data
        service.getEmployees().addAll(FileUtil.loadFromFile());

        while (true) {
            System.out.println("\n--- EMS MENU ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Process Salary");
            System.out.println("7. Save Data");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Salary: ");
                        double salary = sc.nextDouble();

                        System.out.print("Type (1-Permanent, 2-Contract): ");
                        int type = sc.nextInt();

                        if (type == 1)
                            service.addEmployee(new PermanentEmployee(id, name, salary));
                        else
                            service.addEmployee(new ContractEmployee(id, name, salary));
                        break;

                    case 2:
                        service.viewEmployees();
                        break;

                    case 3:
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New Name: ");
                        name = sc.nextLine();

                        System.out.print("New Salary: ");
                        salary = sc.nextDouble();

                        service.updateEmployee(id, name, salary);
                        break;
                        
                        

                    case 4:
                        System.out.print("Enter ID: ");
                        service.deleteEmployee(sc.nextInt());
                        break;

                    case 5:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        service.searchByName(sc.nextLine());
                        break;

                    case 6:
                        service.processSalary();
                        break;

                    case 7:
                    	FileUtil.saveToFile(service.getEmployees());
                        break;

                    case 8:
                    	FileUtil.saveToFile(service.getEmployees());
                        System.out.println("Exiting...");
                        return;
                }

            } catch (EmployeeNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}