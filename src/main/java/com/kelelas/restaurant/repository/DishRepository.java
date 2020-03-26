package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query(value = com.kelelas.restaurant.config.Query.SELECT_ENG_DISHES)
    List<DishDTO> findEngDishes();
    @Query(value = com.kelelas.restaurant.config.Query.SELECT_UKR_DISHES)
    List<DishDTO> findUkrDishes();
    @Query(value = com.kelelas.restaurant.config.Query.SELECT_ONE_ENG_DISH)
    DishDTO findEngDishById(Long id);
    @Query(value = com.kelelas.restaurant.config.Query.SELECT_ONE_UKR_DISH)
    DishDTO findUkrDishById(Long id);
}
