package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.dto.DishDTO;
import com.kelelas.restaurant.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

}
