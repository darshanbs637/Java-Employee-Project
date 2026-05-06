package com.ibm.ems.service;

import com.ibm.ems.model.*;
import com.ibm.ems.exception.EmployeeNotFoundException;

import java.util.*;
import java.util.concurrent.*;

public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    // Add Employee
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    // View All Employees
    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    // Find Employee By ID
    public Employee findById(int id) throws EmployeeNotFoundException {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }

        throw new EmployeeNotFoundException("Employee not found!");
    }

    // Update Employee Details
    public void updateEmployee(int id, String name, double salary)
            throws EmployeeNotFoundException {

        Employee e = findById(id);

        e.setName(name);
        e.setSalary(salary);
    }

    // Search Employee By Name
    public void searchByName(String name) {

        boolean found = false;

        for (Employee e : employees) {

            if (e.getName().equalsIgnoreCase(name)) {

                System.out.println(e);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No match found.");
        }
    }

    // Delete Employee
    public void deleteEmployee(int id) throws EmployeeNotFoundException {

        Employee e = findById(id);

        // Remove employee from list
        employees.remove(e);
    }

    // Return Employee List
    public List<Employee> getEmployees() {
        return employees;
    }

    // Count Total Employees
    public int getEmployeeCount() {
        return employees.size();
    }

    // Get Employee With Highest Salary
    public Employee getHighestSalaryEmployee() {

        if (employees.isEmpty()) {
            return null;
        }

        Employee highest = employees.get(0);

        for (Employee e : employees) {

            if (e.getSalary() > highest.getSalary()) {
                highest = e;
            }
        }

        return highest;
    }

    // Check Whether Employee Exists
    public boolean employeeExists(int id) {

        for (Employee e : employees) {

            if (e.getId() == id) {
                return true;
            }
        }

        return false;
    }

    // Calculate Total Salary Of Employees
    public double getTotalSalary() {

        double totalSalary = 0;

        for (Employee e : employees) {

            totalSalary += e.getSalary();
        }

        return totalSalary;
    }

    // Process Salary Using Multithreading
    public void processSalary() {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (Employee e : employees) {

            executor.submit(() -> {

                double total = e.calculateSalary();

                System.out.println(
                        e.getName() + " Final Salary: " + total
                );
            });
        }

        executor.shutdown();
    }
}