package com.kelelas.restaurant.repository;


import com.kelelas.restaurant.entity.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoriesRepository  extends JpaRepository<HistoryItem, Long> {
}
