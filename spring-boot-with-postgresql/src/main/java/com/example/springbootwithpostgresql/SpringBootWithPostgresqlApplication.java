package com.example.springbootwithpostgresql;

import com.example.springbootwithpostgresql.models.Customer;
import com.example.springbootwithpostgresql.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootWithPostgresqlApplication {

    private final Logger logger = Logger.getLogger("TestApp");

    @Autowired
    private CustomerRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        List allCustomers = this.repository.findAll();
        logger.info("Number of customers: " + allCustomers.size());

        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");
        logger.info("Saving new customer...");
        this.repository.save(newCustomer);

        allCustomers = this.repository.findAll();
        logger.info("Number of customers: " + allCustomers.size());
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithPostgresqlApplication.class, args);
    }

}
