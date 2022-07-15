package org.ifralou.resttry.controllers;

import org.ifralou.resttry.errors.exceptions.NoSuchResource;
import org.ifralou.resttry.persistency.entities.Order;
import org.ifralou.resttry.persistency.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    private List<Order> all() {
       return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    private Order one(@PathVariable int id) {
        return orderRepository.findById(id).orElseThrow(() -> new NoSuchResource(List.of(id)));
    }
}
