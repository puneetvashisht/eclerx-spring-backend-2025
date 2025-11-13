package com.pv.colls;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Fruit {
    String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fruit fruit = (Fruit) obj;
        return name != null ? name.equals(fruit.name) : fruit.name == null;
    }
    
}

public class ArrayListDemo {

    
    
    public static void main(String[] args) {
        
        List<Fruit> list = new ArrayList<>();
        list.add(new Fruit("Apple"));
        list.add(new Fruit("Banana"));
        list.add(new Fruit("Cherry"));
        System.out.println("List contents: " + list);


        boolean isPresent = list.contains(new Fruit("Apple")); 
        System.out.println(isPresent ? "Apple is present" : "Apple is not present"); 
      }   
}
