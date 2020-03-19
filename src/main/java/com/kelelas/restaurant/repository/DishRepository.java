package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.entity.Dish;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
