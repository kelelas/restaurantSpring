package com.kelelas.restaurant.repository;


import com.kelelas.restaurant.entity.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoriesRepository  extends JpaRepository<HistoryItem, Long> {
 List<HistoryItem> getHistoryItemsByStatus(int status);
 List<HistoryItem> getHistoryItemsByUserid(Long userid);
 List<HistoryItem> getHistoryItemsByStatusAndUserid(int status, Long userid);

}
