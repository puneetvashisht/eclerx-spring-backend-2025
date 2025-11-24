package com.pv.spring_boot_security_demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AppUserRepository  extends JpaRepository<AppUser, Integer>{
    Optional<AppUser> findByUsername(String username);

    
}
