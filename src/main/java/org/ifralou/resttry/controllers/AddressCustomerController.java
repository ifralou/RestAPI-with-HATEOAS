package org.ifralou.resttry.controllers;

import org.ifralou.resttry.persistency.entities.Address;
import org.ifralou.resttry.persistency.repositories.AddressRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/address")
public class AddressCustomerController {

    private final AddressRepository addressRepository;

    public AddressCustomerController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @PostMapping("/{customerId}")
    public EntityModel<Address> addAddress(
            @PathVariable int customerId,
            @RequestBody Address newAddress
    ) {
        newAddress.setId(customerId);
        var address = addressRepository.save(newAddress);
        return EntityModel.of(address,
                linkTo(methodOn(CustomerController.class).one(customerId)).withRel("customer")
        );
    }
}
