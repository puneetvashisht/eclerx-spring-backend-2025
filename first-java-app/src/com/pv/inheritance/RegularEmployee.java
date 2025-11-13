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
    @Override
     public void incrementSalary(int percentage) {
        // Not applicable
        this.salary *=  percentage;
    }
    @Override
    public void foodCoupons() {
       System.out.println("Food coupons for Regular Employee");
    }
   
     
   
    
}
