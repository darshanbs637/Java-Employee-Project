package com.ibm.ems.model;

public class PermanentEmployee extends Employee {

    public PermanentEmployee(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public double calculateSalary() {
        return salary + (salary * 0.10);
    }
}