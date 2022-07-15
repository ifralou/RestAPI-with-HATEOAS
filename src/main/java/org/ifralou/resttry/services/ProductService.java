package org.ifralou.resttry.services;

import org.ifralou.resttry.DTOs.ProductDTO;
import org.ifralou.resttry.errors.exceptions.UserNotFoundException;
import org.ifralou.resttry.persistency.entities.Product;
import org.ifralou.resttry.persistency.repositories.ProductRepository;
import org.ifralou.resttry.services.mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
   private final ProductRepository productRepository;
   private final CategoryService categoryService;
   private final Mapper mapper;


   public ProductService(
           ProductRepository productRepository,
           CategoryService categoryRepository,
           Mapper mapper
   ) {
      this.mapper = mapper;
      this.productRepository = productRepository;
      this.categoryService = categoryRepository;
   }

   public List<ProductDTO> all() {
      return StreamSupport
              .stream(productRepository.findAll().spliterator(), false)
              .map(mapper::toDTO)
              .toList();
   }

   //TODO: Problems with identity from hibernate.
   /*
      remap entities?
      rewrite identity functions?
      try loop through?
   */
   public ProductDTO addProduct(ProductDTO model) {
     return mapper.toDTO(
             StreamSupport.stream(productRepository.findAll().spliterator(), false)
              .filter(entity -> Objects.equals(entity.getName(), model.name()))
              .findFirst()
              .orElse(productRepository.save(mapper.toEntity(model)))
     );
   }

   public ProductDTO getProduct(int id)  {
       return mapper.toDTO(getProductEntity(id));
   }

   private Product getProductEntity(int id) {
       return productRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
   }

   public ProductDTO updateProduct(int id, ProductDTO addedProduct) {
     return mapper.toDTO(
             productRepository.findById(id)
               .map(product -> {
                  product.setPrice(addedProduct.price());
                  product.setName(addedProduct.name());
                  product.setSku(addedProduct.name().toLowerCase(Locale.ROOT));
                  return productRepository.save(product);
               }).orElse(productRepository.save(mapper.toEntity(id, addedProduct)))
     );
   }

   public void deleteProduct(int id) {
       productRepository.deleteById(id);
   }

   public ProductDTO addCategoriesToProduct(int productId, List<Integer> values) {
       var product = getProductEntity(productId);
       product.getCategories().addAll(categoryService.getAllCategoryEntitiesWithIds(values));
       return mapper.toDTO(productRepository.save(product));
   }
}
