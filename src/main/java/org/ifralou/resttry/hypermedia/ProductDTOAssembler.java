package org.ifralou.resttry.hypermedia;

import org.ifralou.resttry.DTOs.ProductDTO;
import org.ifralou.resttry.controllers.ProductController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductDTOAssembler implements RepresentationModelAssembler<ProductDTO, EntityModel<ProductDTO>> {

    @Override
    public EntityModel<ProductDTO> toModel(ProductDTO entity) {
       return EntityModel.of(entity,
               linkTo(methodOn(ProductController.class).one(entity.id())).withSelfRel(),
               linkTo(methodOn(ProductController.class).all()).withRel("products")
       );
    }
}
