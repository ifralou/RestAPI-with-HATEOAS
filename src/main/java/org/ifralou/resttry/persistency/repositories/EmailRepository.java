package org.ifralou.resttry.persistency.repositories;


import org.ifralou.resttry.persistency.entities.EmailAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends CrudRepository<EmailAddress, Integer> {
    List<EmailAddress> findAllByCustomerId(int id);
    Optional<EmailAddress> findById(int id);
    Optional<EmailAddress> findByCustomerIdAndId(int customerId, int id);
    void deleteById(int id);
}
