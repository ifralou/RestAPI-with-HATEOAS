package org.ifralou.resttry.services;

import org.ifralou.resttry.DTOs.CategoryDTO;
import org.ifralou.resttry.errors.exceptions.CategoryAlreadyExists;
import org.ifralou.resttry.errors.exceptions.NoSuchResource;
import org.ifralou.resttry.errors.exceptions.SomeCategoriesAreLost;
import org.ifralou.resttry.persistency.entities.Category;
import org.ifralou.resttry.persistency.repositories.CategoryRepository;
import org.ifralou.resttry.services.mappers.Mapper;
import org.ifralou.resttry.services.mappers.SimpleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final Mapper mapper;

    public CategoryService(
            CategoryRepository categoryRepository,
            SimpleMapper mapper
    ) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> findAll() {
       return categoryRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public CategoryDTO getProductById(int id) {
       return mapper.toDTO(categoryRepository.findById(id).orElseThrow(() -> new NoSuchResource(List.of(id))));
    }

    public List<Category> getAllCategoryEntitiesWithIds(List<Integer> ids) {
       var categories = categoryRepository.findCategoriesByCategoryIdIn(ids);
       if(categories.isEmpty()) {
           throw new NoSuchResource(ids);
       }
       if(categories.size() < ids.size()) {
           throw new SomeCategoriesAreLost();
       }
       return categories;
    }


    public CategoryDTO addCategory(CategoryDTO newCategory) {
        var cat = categoryRepository.findCategoryByName(newCategory.name());
        if(cat.isPresent()) {
            throw new CategoryAlreadyExists(cat.get().getName());
        }
        return mapper.toDTO(
                categoryRepository.save(mapper.toEntity(newCategory))
        );
    }
    //TODO: Why category is not optional?
    public CategoryDTO mergeCategory(int id, CategoryDTO updatedCategory) {
        return mapper.toDTO(
                categoryRepository.findById(id).map(cat -> {
                    cat.setName(updatedCategory.name());
                    cat.setDescription(updatedCategory.description());
                    return categoryRepository.save(cat);
                }).orElseThrow(() -> new NoSuchResource(List.of(id)))
        );
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
