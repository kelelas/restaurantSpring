package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.StoriesDTO;
import com.kelelas.restaurant.entity.Dish;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.repository.StoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StoriesService {
    StoriesRepository storiesRepository;
    @Autowired
    public StoriesService(StoriesRepository storiesRepository) {
        this.storiesRepository = storiesRepository;
    }
    public List<HistoryItem> getAllStories() {
        return storiesRepository.findAll();
    }
    public Optional<HistoryItem> getStoryById(Long id){
        return storiesRepository.findById(id);
    }
    public List<HistoryItem> getStoryByUserId(Long userid){
        return storiesRepository.getHistoryItemsByUserid(userid);
    }
    public void save(HistoryItem item){
        storiesRepository.save(item);
    }
    public List<HistoryItem> getStoriesByStatus(int status){
        return storiesRepository.getHistoryItemsByStatus(status);
    }
    public List<HistoryItem> getStoriesByStatusAndId(int status, Long id){
        return storiesRepository.getHistoryItemsByStatusAndUserid(status, id);
    }


}
