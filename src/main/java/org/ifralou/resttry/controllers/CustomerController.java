package org.ifralou.resttry.controllers;

import org.apache.juli.logging.Log;
import org.ifralou.resttry.DTOs.CustomerDTO;
import org.ifralou.resttry.errors.exceptions.NoSuchResource;
import org.ifralou.resttry.hypermedia.CustomerDTOAssembler;
import org.ifralou.resttry.persistency.entities.Customer;
import org.ifralou.resttry.persistency.repositories.CustomerRepository;
import org.ifralou.resttry.persistency.repositories.EmailRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerDTOAssembler customerAssembler;
    private final EmailRepository emailRepository;

    public CustomerController(
            CustomerRepository customerRepository,
            EmailRepository emailRepository,
            CustomerDTOAssembler assembler
    ) {
        this.customerRepository = customerRepository;
        this.emailRepository = emailRepository;
        this.customerAssembler = assembler;
    }

    @GetMapping
    public List<EntityModel<CustomerDTO>> all()  {
       return customerRepository.findAll()
               .stream().map(customerAssembler::toModel)
               .toList();
    }

    @GetMapping("/{id}")
    public EntityModel<CustomerDTO> one(@PathVariable int id) {
        return customerAssembler.toModel(
                customerRepository.findById(id).orElseThrow(() -> new NoSuchResource(List.of(id)))
        );
    }

    @PostMapping
    public EntityModel<CustomerDTO> postOne(@RequestBody Customer customer) {
       return customerAssembler.toModel(
           customerRepository
               .findByNameAndBirthdate(customer.getName(), customer.getBirthdate())
               .orElse(customerRepository.save(customer))
       );
    }

    @PutMapping("/{id}")
    private Customer updateCustomerCredentials(
            @PathVariable int id,
            @RequestBody Customer customer
    ) {
        Logger.getLogger(CustomerController.class.getName()).info(customer.toString());
        var existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchResource(List.of(id)));
        existingCustomer.setName(customer.getName());
        existingCustomer.setBirthdate(customer.getBirthdate());
        return customerRepository.save(existingCustomer);
    }

    //TODO: Fix hibernate thing
    @DeleteMapping("/{id}")
    private void deleteCustomer(
            @PathVariable int id
    ) {
        var existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchResource(List.of(id)));
        customerRepository.delete(existingCustomer);
    }


}