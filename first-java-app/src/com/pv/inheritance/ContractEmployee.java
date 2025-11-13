package com.pv.inheritance;

public class ContractEmployee extends Employee {
    
    int payPerDay;

    public ContractEmployee(int id, String name, int payPerDay) {
        super(id, name);
        this.payPerDay = payPerDay;
    }

    @Override
    public String toString() {
        return "ContractEmployee{payPerDay=" + payPerDay + ", id=" + id + ", name='" + name + "'}";
    }

    public void incrementSalary(int amount) {
        // Not applicable
        this.payPerDay += amount;
    }

    @Override
    public void foodCoupons() {
        System.out.println("Food coupons for Contract Employee");
    }

    

}
