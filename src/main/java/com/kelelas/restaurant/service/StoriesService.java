package com.kelelas.restaurant.service;

import com.kelelas.restaurant.dto.StoriesDTO;
import com.kelelas.restaurant.repository.StoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoriesService {
    StoriesRepository storiesRepository;
    @Autowired
    public StoriesService(StoriesRepository storiesRepository) {
        this.storiesRepository = storiesRepository;
    }
    public StoriesDTO getAllStories() {
        return new StoriesDTO(storiesRepository.findAll());
    }
}
