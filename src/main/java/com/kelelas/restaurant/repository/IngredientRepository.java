package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient getById(Long id);
}
