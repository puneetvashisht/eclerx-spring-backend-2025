package com.pv.colls;
import java.util.ArrayList;
import java.util.List;

import com.pv.inheritance.ContractEmployee;
import com.pv.inheritance.Employee;
import com.pv.inheritance.RegularEmployee;

public class EmployeeOperations {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        // Add employees to the list
        employees.add(new RegularEmployee(1, "Alice", 20000));
        employees.add(new RegularEmployee(2, "Bob", 60000));
        employees.add(new ContractEmployee(34, "Ankit", 3000)); 
        employees.add(new ContractEmployee(4, "David", 4000));  
        
        // System.out.println(employees);

        System.out.println("---- Employees with name starting with A ----");
        for (Employee emp : employees) {
            if(emp.name.startsWith("A")) {
                System.out.println(emp);
            }
        }

        System.out.println("---- Employees with id greater than 10 ----");
         for (Employee emp : employees) {
            if(emp.id>10) {
                System.out.println(emp);
            }
        }

        System.out.println("----- Print all employees-----");

        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
