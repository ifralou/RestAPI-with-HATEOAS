package org.ifralou.resttry.services.mappers;

import org.ifralou.resttry.DTOs.CategoryDTO;
import org.ifralou.resttry.DTOs.ProductDTO;
import org.ifralou.resttry.persistency.entities.Category;
import org.ifralou.resttry.persistency.entities.Product;

public interface Mapper {
    ProductDTO toDTO(Product entity);
    CategoryDTO toDTO(Category entity);

    Product toEntity(ProductDTO model);
    Product toEntity(int id, ProductDTO model);

    Category toEntity(CategoryDTO newCategory);
}
