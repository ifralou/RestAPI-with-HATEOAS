package org.ifralou.resttry.controllers;

import org.ifralou.resttry.persistency.entities.Order;
import org.ifralou.resttry.persistency.repositories.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{customerId}/orders")
public class OrderCustomerController {

    private final OrderRepository repository;

    public OrderCustomerController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Order> all(@PathVariable int customerId) {
        return repository.findAllByCustomerId(customerId);
    }
}
