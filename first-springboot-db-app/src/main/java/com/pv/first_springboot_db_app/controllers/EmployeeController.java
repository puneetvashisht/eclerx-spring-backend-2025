package com.pv.first_springboot_db_app.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.pv.first_springboot_db_app.entities.Employee;
import com.pv.first_springboot_db_app.repository.EmployeeRepository;
import com.pv.first_springboot_db_app.utils.EmployeeNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    
    @Autowired
    EmployeeRepository employeeRepository;
 
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee emp) {
        employeeRepository.save(emp);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        //implement HttpStatus 404 for records not found
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    @PatchMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmp) {
        Employee existingEmp = employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found"));
        
        if (updatedEmp.getSalary() != 0) {
            existingEmp.setSalary(updatedEmp.getSalary());
        }

        employeeRepository.save(existingEmp);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }   
}