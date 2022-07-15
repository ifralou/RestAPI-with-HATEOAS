package org.ifralou.resttry.controllers;

import org.ifralou.resttry.DTOs.MainDTO;
import org.ifralou.resttry.persistency.entities.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MainController {
   @GetMapping
   private EntityModel<MainDTO> main() {
      var main = new MainDTO(
             "Welcome!",
              "Take a look what you could do with it."
      );
      return EntityModel.of(main,
              linkTo(methodOn(ProductController.class).all()).withRel("products"),
              linkTo(methodOn(CustomerController.class).all()).withRel("customers")
      );
   }
}
