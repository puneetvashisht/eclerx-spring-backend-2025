package com.pv.first_springboot_db_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pv.first_springboot_db_app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
