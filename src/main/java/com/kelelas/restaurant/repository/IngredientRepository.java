package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.dto.IngredientDTO;
import com.kelelas.restaurant.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient getById(Long id);
    @Query(value = com.kelelas.restaurant.config.Query.SELECT_ENG_INGREDIENTS)
    List<IngredientDTO> getEngIngredients();
    @Query(value = com.kelelas.restaurant.config.Query.SELECT_UKR_INGREDIENTS)
    List<IngredientDTO> getUkrIngredients();
}
