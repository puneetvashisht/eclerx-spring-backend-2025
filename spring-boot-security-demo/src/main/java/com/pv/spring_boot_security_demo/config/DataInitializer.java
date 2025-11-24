package com.pv.spring_boot_security_demo.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pv.spring_boot_security_demo.user.AppUser;
import com.pv.spring_boot_security_demo.user.AppUserRepository;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(AppUserRepository repo,
                                       PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                AppUser user = new AppUser();
                user.setUsername("user");
                user.setPassword(encoder.encode("userpass"));
                user.getRoles().add("USER");

                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.getRoles().add("ADMIN");

                repo.saveAll(List.of(user, admin));
            }
        };
    }
}
