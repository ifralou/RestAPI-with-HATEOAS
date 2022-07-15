package org.ifralou.resttry.services.mappers;

import org.ifralou.resttry.DTOs.CategoryDTO;
import org.ifralou.resttry.DTOs.ProductDTO;
import org.ifralou.resttry.persistency.entities.Category;
import org.ifralou.resttry.persistency.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Locale;

@Component
public class SimpleMapper implements Mapper{

    public ProductDTO toDTO(Product entity) {
        return new ProductDTO(
          entity.getProduct_id(),
          entity.getName(),
          entity.getPrice(),
          entity.getCategories().stream()
                  .map(this::toDTO)
                  .sorted(Comparator.comparing(CategoryDTO::id)).toList()
        );
    }

    public CategoryDTO toDTO(Category entity) {
       return new CategoryDTO(
        entity.getCategoryId(),
        entity.getName(),
        entity.getDescription()
       );
    }

    @Override
    public Product toEntity(ProductDTO model) {
        var newProduct = new Product();
        newProduct.setName(model.name());
        newProduct.setPrice(model.price());
        newProduct.setSku(model.name().toLowerCase(Locale.ROOT));
        return newProduct;
    }

    @Override
    public Product toEntity(int id, ProductDTO model) {
        var entity = toEntity(model);
        entity.setProduct_id(id);
        return entity;
    }

    @Override
    public Category toEntity(CategoryDTO model) {
        var categoryEntity = new Category();
        categoryEntity.setName(model.name());
        categoryEntity.setDescription(model.description());
        return categoryEntity;
    }


}
