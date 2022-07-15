package org.ifralou.resttry.persistency.repositories;

import org.ifralou.resttry.persistency.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();
    Optional<Customer> findByNameAndBirthdate(String name, LocalDate birthDate);
}
