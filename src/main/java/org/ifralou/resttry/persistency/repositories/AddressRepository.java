package org.ifralou.resttry.persistency.repositories;

import org.ifralou.resttry.persistency.entities.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    List<Address> findAll();
    Optional<Address> findById(int id);
}
