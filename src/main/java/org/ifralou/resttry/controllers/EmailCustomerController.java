package org.ifralou.resttry.controllers;

import org.ifralou.resttry.errors.exceptions.NoSuchResource;
import org.ifralou.resttry.persistency.entities.EmailAddress;
import org.ifralou.resttry.persistency.repositories.EmailRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/{customerId}/emailAddresses")
public class EmailCustomerController {

    EmailRepository emailRepository;

    public EmailCustomerController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @GetMapping
    public List<EntityModel<EmailAddress>> all(@PathVariable int customerId) {
        return emailRepository.findAllByCustomerId(customerId).stream().map(
                emailAddress -> EntityModel.of(emailAddress,
                        linkTo(methodOn(EmailCustomerController.class).one(customerId, emailAddress.getId())).withSelfRel()
                ))
                .toList();
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

}
