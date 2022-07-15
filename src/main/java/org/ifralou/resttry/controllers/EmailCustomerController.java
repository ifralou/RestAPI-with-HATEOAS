package org.ifralou.resttry.controllers;

import org.ifralou.resttry.errors.exceptions.NoSuchResource;
import org.ifralou.resttry.hypermedia.EmailAddressAssembler;
import org.ifralou.resttry.persistency.entities.EmailAddress;
import org.ifralou.resttry.persistency.repositories.CustomerRepository;
import org.ifralou.resttry.persistency.repositories.EmailRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/{customerId}/emailAddresses")
public class EmailCustomerController {

    private final EmailRepository emailRepository;
    private final EmailAddressAssembler emailAssembler;
    public EmailCustomerController(
            EmailRepository emailRepository,
            EmailAddressAssembler emailAssembler,
            CustomerRepository customerRepository) {
        this.emailRepository = emailRepository;
        this.emailAssembler = emailAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<EmailAddress>> all(@PathVariable int customerId) {
        return CollectionModel.of( emailRepository.findAllByCustomerId(customerId).stream().map(ea -> emailAssembler.toModel(ea, customerId)).toList(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customers")
        );
    }

    @GetMapping("/{emailId}")
    public EntityModel<EmailAddress> one(
            @PathVariable int customerId,
            @PathVariable int emailId
    ) {
        var email = emailRepository.findById(emailId).orElseThrow(() -> new NoSuchResource(List.of(emailId)));
        return EntityModel.of(email,
                linkTo(methodOn(EmailCustomerController.class).all(customerId)).withRel("emails")
        );
    }

    @PostMapping
    public EntityModel<EmailAddress> addOne(
            @PathVariable int customerId,
            @RequestBody EmailAddress newAddress
    ) {
       newAddress.setCustomerId(customerId);
       return EntityModel.of(emailRepository.save(newAddress),
              linkTo(methodOn(EmailCustomerController.class).all(customerId)).withRel("emails")
       );
    }

    @PutMapping("/{emailId}")
    public EntityModel<EmailAddress> patchOne(
            @PathVariable int customerId,
            @PathVariable int emailId,
            @RequestBody EmailAddress newEmail
    ) {
        var email = emailRepository.findByCustomerIdAndId(customerId, emailId).orElseThrow(() -> new NoSuchResource(List.of(emailId)));
        email.setAddress(newEmail.getAddress());
        return EntityModel.of(emailRepository.save(email),
                linkTo(methodOn(EmailCustomerController.class).one(customerId, emailId)).withSelfRel(),
                linkTo(methodOn(EmailCustomerController.class).all(customerId)).withRel("emails")
        );
    }

    @DeleteMapping("/{emailId}")
    public CollectionModel<EntityModel<EmailAddress>> deleteOne(
            @PathVariable int customerId,
            @PathVariable int emailId
    ) {
       emailRepository.deleteById(emailId);
       return all(customerId);
    }

}
