package org.ifralou.resttry.persistency.repositories;

import org.ifralou.resttry.persistency.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Override
    Iterable<Product> findAll();
    @Override
    Optional<Product> findById(Integer integer);

    @Override
    Product save(Product entity);
}

