package org.ifralou.resttry;

import org.ifralou.resttry.persistency.entities.Customer;
import org.ifralou.resttry.persistency.entities.Order;
import org.ifralou.resttry.persistency.repositories.AddressRepository;
import org.ifralou.resttry.persistency.repositories.CustomerRepository;
import org.ifralou.resttry.persistency.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTryApplication.class, args);
    }

}
