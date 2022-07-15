package org.ifralou.resttry.persistency.repositories;

import org.ifralou.resttry.persistency.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Integer> {
   List<Order> findAll();
   Optional<Order> findById(int id);

   List<Order> findAllByCustomerId(int id);
}
