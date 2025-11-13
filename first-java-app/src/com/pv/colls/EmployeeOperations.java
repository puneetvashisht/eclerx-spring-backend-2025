package com.pv.colls;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.pv.inheritance.ContractEmployee;
import com.pv.inheritance.Employee;
import com.pv.inheritance.RegularEmployee;

@FunctionalInterface
interface Condition{
    boolean test(Employee e);
}

class IdGreaterThanTen implements Condition{
    @Override
    public boolean test(Employee e){
        return e.id>10;
    }
}

public class EmployeeOperations {

    public static void printEmployees(List<Employee> employees, Predicate<Employee> condition) {
        for (Employee emp : employees) {
            if(condition.test(emp)){
                System.out.println(emp);
            }
        }
    }
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        // Add employees to the list
        employees.add(new RegularEmployee(1, "Alice", 20000));
        employees.add(new RegularEmployee(2, "Bob", 60000));
        employees.add(new ContractEmployee(34, "Ankit", 3000)); 
        employees.add(new ContractEmployee(4, "David", 4000));
        
        

        // select * from employee where name='A%'
        Optional<Employee> employeeFound = employees.stream()
        .filter(e -> e.name.startsWith("A"))
        .max((e1, e2) -> e1.id.compareTo(e2.id));

        employeeFound.ifPresent(e -> System.out.println("Employee found: " + e));


        employees.stream()
        .map(e -> e.id)
        .sorted()
        .forEach(e -> System.out.println(e));
        
        // System.out.println(employees);

        System.out.println("---- Employees with name starting with A ----");
        // for (Employee emp : employees) {
        //     if(emp.name.startsWith("A")) {
        //         System.out.println(emp);
        //     }
        // }
        // printEmployees(employees, new Condition(){
        //     @Override
        //     public boolean test(Employee e){
        //         return e.name.startsWith("A");
        //     }
        // });
        // Condition nameStartsWithA = (e) -> e.name.startsWith("A");
        // printEmployees(employees, (e) -> e.name.startsWith("A"));

        System.out.println("---- Employees with id greater than 10 ----");
        //  for (Employee emp : employees) {
        //     if(emp.id>10) {
        //         System.out.println(emp);
        //     }
        // }
        // printEmployees(employees, new IdGreaterThanTen());
        // printEmployees(employees, (e) -> e.id > 10);

        System.out.println("----- Print all employees-----");
        // printEmployees(employees, new Condition(){
        //     @Override
        //     public boolean test(Employee e){
        //         return true;
        //     }
        // });
        // printEmployees(employees, (e) -> true);
        // for (Employee emp : employees) {
        //     if(true){
        //         System.out.println(emp);
        //     }
           
        // }
    }
}
