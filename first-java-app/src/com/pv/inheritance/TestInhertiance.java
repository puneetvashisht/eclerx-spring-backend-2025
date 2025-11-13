package com.pv.inheritance;

public class TestInhertiance {
    public static void main(String[] args) {
        
        Employee employees[] = new Employee[2];

        ContractEmployee ce = new ContractEmployee(101, "John", 10000);
        ce.incrementSalary(5000);
        System.out.println(ce.toString());
        employees[0] = ce;

        RegularEmployee re = new RegularEmployee(102, "Jane", 60000);
        re.incrementSalary(1.1);
        System.out.println(re.toString());
        employees[1] = re;

        System.out.println("---- Employees List ----");
        for(Employee e : employees) {
            System.out.println(e);
            e.incrementSalary(10);
            System.out.println("After increment: " + e);
        }   
    }
}
