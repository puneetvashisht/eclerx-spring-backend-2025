package com.pv.colls;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.pv.inheritance.Employee;
import com.pv.inheritance.RegularEmployee;

public class SetsAndMapsDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
         set.add("Cherry");
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");   

        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(1, new RegularEmployee(1, "Alice", 34300));
        employeeMap.put(2, new RegularEmployee(2, "Bob", 45000));

        System.out.println(employeeMap.get(2));
       
        System.out.println("Set contents: " + set);
    }
}
