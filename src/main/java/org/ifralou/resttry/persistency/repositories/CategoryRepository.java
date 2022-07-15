package org.ifralou.resttry.persistency.repositories;

import org.ifralou.resttry.persistency.entities.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findCategoriesByCategoryIdIn(List<Integer> ids);

    List<Category> findAll();
    //Category findById(int id);

    @Query(value = "from Category c where c.name = :name")
    Optional<Category> findCategoryByName(String name);
    Optional<Category> findByCategoryId(int categoryId);
}
