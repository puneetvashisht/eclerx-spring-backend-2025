package com.pv.spring_boot_security_demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class HelloController {
    
    @GetMapping("/public/hello")
    public String sayHello() {
       return "Hello from App!";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "Dashboard!";
    }

     @GetMapping("/secured/hello")
    public String saySecuredHello() {
       return "Hello from Secured App!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reports")
    public String reports(){
        return "Sensitive info/reports";
    }
    

}
