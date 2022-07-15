package org.ifralou.resttry.hypermedia;

import org.ifralou.resttry.DTOs.CustomerDTO;
import org.ifralou.resttry.controllers.CustomerController;
import org.ifralou.resttry.controllers.EmailCustomerController;
import org.ifralou.resttry.controllers.OrderCustomerController;
import org.ifralou.resttry.persistency.entities.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerDTOAssembler implements RepresentationModelAssembler<Customer, EntityModel<CustomerDTO>> {
    @Override
    public EntityModel<CustomerDTO> toModel(Customer entity) {
        var customerId = entity.getCustomerId();
        return EntityModel.of(new CustomerDTO(customerId, entity.getName(), entity.getBirthdate(), entity.getAddress()),
                linkTo(methodOn(CustomerController.class).one(entity.getCustomerId())).withSelfRel(),
                linkTo(methodOn(EmailCustomerController.class).all(customerId)).withRel("emails"),
                linkTo(methodOn(OrderCustomerController.class).all(customerId)).withRel("orders")
        );
    }
}
