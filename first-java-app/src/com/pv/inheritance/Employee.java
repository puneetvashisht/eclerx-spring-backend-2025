package com.pv.inheritance;

public abstract class Employee implements Perks {
    public Integer id;
    public String name;

    public Employee() {
    }
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // public void incrementSalary(int amount) {
    //     // Default implementation (can be overridden)
    // }

    public abstract void incrementSalary(int value);

     @Override
    public void healthInsurance() {
        System.out.println("Health insurance for Employee");
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}
