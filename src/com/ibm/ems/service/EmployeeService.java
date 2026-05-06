package com.ibm.ems.service;
import com.ibm.ems.exception.EmployeeNotFoundException;
import com.ibm.ems.model.*;
import java.util.*;
import java.util.concurrent.*;


public class EmployeeService {
	

	    private List<Employee> employees = new ArrayList<>();

	    // Add
	    public void addEmployee(Employee emp) {
	        employees.add(emp);
	    }

	    // View
	    public void viewEmployees() {
	        if (employees.isEmpty()) {
	            System.out.println("No employees found.");
	            return;
	        }
	        for (Employee e : employees) {
	            System.out.println(e);
	        }
	    }

	    // Find by ID
	    public Employee findById(int id) throws EmployeeNotFoundException {
	        for (Employee e : employees) {
	            if (e.getId() == id) return e;
	        }
	        throw new EmployeeNotFoundException("Employee not found!");
	    }

	    // Update
	    public void updateEmployee(int id, String name, double salary)
	            throws EmployeeNotFoundException {
	        Employee e = findById(id);
	        e.setName(name);
	        e.setSalary(salary);
	    }



	    // Search by Name
	    public void searchByName(String name) {
	        boolean found = false;
	        for (Employee e : employees) {
	            if (e.getName().equalsIgnoreCase(name)) {
	                System.out.println(e);
	                found = true;
	            }
	        }
	        if (!found) System.out.println("No match found.");
	    }

			    // Delete
	    public void deleteEmployee(int id) throws EmployeeNotFoundException {
	        Employee e = findById(id);
			//delete employee
	        employees.remove(e);
	    }

	    public List<Employee> getEmployees() {
	        return employees;
	    }

	    // Multithreading Salary Processing
	    public void processSalary() {
	        ExecutorService executor = Executors.newFixedThreadPool(3);

	        for (Employee e : employees) {
	            executor.submit(() -> {
	                double total = e.calculateSalary();
	                System.out.println(e.getName() + " Final Salary: " + total);
					e.setSalary(total);
	            });
	        }

	        executor.shutdown();
	    }
	}

