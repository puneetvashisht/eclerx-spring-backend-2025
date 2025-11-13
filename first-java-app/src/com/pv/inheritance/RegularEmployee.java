package com.pv.inheritance;

public class RegularEmployee extends Employee {
    
    int salary;

    public RegularEmployee(int id, String name, int salary) {
        super(id, name);
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "RegularEmployee{salary=" + salary + ", id=" + id + ", name='" + name + "'}";
    }

     public void incrementSalary(double percentage) {
        // Not applicable
        this.salary *=  percentage;
    }
    
}
