package com.pv.inheritance;

public class Employee {
    int id;
    String name;

    public Employee() {
    }
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void incrementSalary(int amount) {
        // Default implementation (can be overridden)
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}
