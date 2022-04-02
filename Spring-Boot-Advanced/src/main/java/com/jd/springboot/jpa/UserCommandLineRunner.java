package com.jd.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory
            .getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) {
        // save a couple of customers
        repository.save(new User("JD", "Admin"));
        repository.save(new User("Ram", "User"));
        repository.save(new User("Satish", "Admin"));
        repository.save(new User("shivam", "User"));

        log.info("-------------------------------");
        log.info("Finding all users");
        log.info("-------------------------------");
        for (User user : repository.findAll()) {
            log.info(user.toString());
        }
        
        log.info("-------------------------------");
        log.info("Finding all Admins");
        log.info("-------------------------------");
        for (User admin : repository.findByRole("Admin")) {
            log.info(admin.toString());
            // Do something...
        }
        

    }

}
