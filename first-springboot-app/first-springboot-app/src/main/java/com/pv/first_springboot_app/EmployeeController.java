package com.pv.first_springboot_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1")
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

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
         // or throw an exception
        throw new EmployeeNotFound("Employee with id " + id + " not found");
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee emp) {
        employees.add(emp);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        //implement HttpStatus 404 for records not found
        employees.removeIf(emp -> emp.getId() == id);
    }

    @PatchMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmp) {
        //implement HttpStatus 404 for records not found
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                employees.set(i, updatedEmp);
                return;
            }
        }
    }
       
}
