package org.ifralou.resttry.hypermedia;

import org.ifralou.resttry.controllers.EmailCustomerController;
import org.ifralou.resttry.persistency.entities.EmailAddress;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmailAddressAssembler implements RepresentationModelAssembler<EmailAddress, EntityModel<EmailAddress>> {

    @Override
    public EntityModel<EmailAddress> toModel(EmailAddress entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(EmailCustomerController.class).one(-1,entity.getId())).withSelfRel(),
                linkTo(methodOn(EmailCustomerController.class).deleteOne(-1,entity.getId())).withRel("delete")
        );
    }

    public EntityModel<EmailAddress> toModel(EmailAddress entity, int customerId) {
        return EntityModel.of(entity,
                linkTo(methodOn(EmailCustomerController.class).one(customerId, entity.getId())).withSelfRel(),
                linkTo(methodOn(EmailCustomerController.class).deleteOne(customerId, entity.getId())).withRel("delete")
        );
    }
}
