package com.kelelas.restaurant.repository;


import com.kelelas.restaurant.dto.StoryDTO;
import com.kelelas.restaurant.entity.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoriesRepository  extends JpaRepository<HistoryItem, Long> {
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_ENG_STORIES_BY_STATUS)
 List<StoryDTO> getEngStoryItemsByStatus(int status);
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_UKR_STORIES_BY_STATUS)
 List<StoryDTO> getUkrStoryItemsByStatus(int status);
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_ENG_STORIES_BY_USERID)
 List<StoryDTO> getEngStoryItemsByUserId(Long userid);
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_UKR_STORIES_BY_USERID)
 List<StoryDTO> getUkrStoryItemsByUserId(Long userid);
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_ENG_STORIES_BY_STATUS_AND_USERID)
 List<StoryDTO> getEngStoryItemsByStatusAndUserId(int status, Long userid);
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_UKR_STORIES_BY_STATUS_AND_USERID)
 List<StoryDTO> getUkrStoryItemsByStatusAndUserId(int status, Long userid);
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_ENG_STORIES)
 List<StoryDTO> getEngStoryItems();
 @Query(value = com.kelelas.restaurant.config.Query.SELECT_UKR_STORIES)
 List<StoryDTO> getUkrStoryItems();
}
