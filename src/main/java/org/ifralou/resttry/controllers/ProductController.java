package org.ifralou.resttry.controllers;

import org.ifralou.resttry.DTOs.ProductDTO;
import org.ifralou.resttry.hypermedia.ProductDTOAssembler;
import org.ifralou.resttry.errors.exceptions.IncompleteInfo;
import org.ifralou.resttry.services.ProductService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductDTOAssembler assembler;

    public ProductController(
            ProductService productService,
            ProductDTOAssembler assembler
    ) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping
    public List<EntityModel<ProductDTO>> all() {
        return productService.all().stream().map(assembler::toModel).toList();
    }

    @GetMapping("/{id}")
    public EntityModel<ProductDTO> one(@PathVariable int id) {
        return assembler.toModel(productService.getProduct(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ProductDTO> addProduct(
            @RequestBody ProductDTO newProduct
    ) {
        if(newProduct.name() == null || newProduct.price() == null) {
            throw new IncompleteInfo();
        }
        return assembler.toModel(productService.addProduct(newProduct));
    }

    @PutMapping("/{id}")
    public ProductDTO replace(
            @PathVariable int id,
            @RequestBody ProductDTO newProduct
    ) {
        return productService.updateProduct(id, newProduct);
    }

    @PutMapping("/{productId}/categorize")
    public EntityModel<ProductDTO> addCategory(
            @PathVariable int productId,
            @RequestParam List<Integer> values
    ) {
        return assembler.toModel(productService.addCategoriesToProduct(productId, values));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable int id) {
        productService.deleteProduct(id);
    }



}