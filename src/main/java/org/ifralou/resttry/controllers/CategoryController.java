package org.ifralou.resttry.controllers;

import org.ifralou.resttry.DTOs.CategoryDTO;
import org.ifralou.resttry.errors.exceptions.IncompleteInfo;
import org.ifralou.resttry.persistency.entities.Category;
import org.ifralou.resttry.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    private List<CategoryDTO> all() {
       return categoryService.findAll();
    }

    @GetMapping("/{id}")
    private CategoryDTO one(@PathVariable int id) {
        return categoryService.getProductById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private CategoryDTO addOne(@RequestBody CategoryDTO newCategory) {
        if(newCategory.name() == null ||  newCategory.description() == null) {
           throw new IncompleteInfo();
        }
        return categoryService.addCategory(newCategory);
    }

    @PutMapping("/{id}")
    private CategoryDTO patchOne(
            @PathVariable int id,
            @RequestBody CategoryDTO updatedCategory
    ) {
        return categoryService.mergeCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteOne(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }

}
