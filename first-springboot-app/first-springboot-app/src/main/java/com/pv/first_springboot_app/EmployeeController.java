package com.pv.first_springboot_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();
    {
        employees.add(new Employee(1, "Alice", 50000));
        employees.add(new Employee(2, "Bob", 60000));   
        employees.add(new Employee(3, "Charlie", 70000));
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee emp) {
        employees.add(emp);
    }
}
