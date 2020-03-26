package com.kelelas.restaurant.service;


import com.kelelas.restaurant.dto.StoryDTO;
import com.kelelas.restaurant.entity.HistoryItem;
import com.kelelas.restaurant.repository.StoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class StoriesService {
    StoriesRepository storiesRepository;
    @Autowired
    public StoriesService(StoriesRepository storiesRepository) {
        this.storiesRepository = storiesRepository;
    }

    public Optional<HistoryItem> getStoryById(Long id){
        return storiesRepository.findById(id);
    }
    public void save(HistoryItem item){
        storiesRepository.save(item);
    }

    public List<StoryDTO> getLocaleStoriesByUserId(HttpServletRequest request, Long userid){
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return storiesRepository.getUkrStoryItemsByUserId(userid);
        else
            return storiesRepository.getEngStoryItemsByUserId(userid);
    }

    public List<StoryDTO> getLocaleStoriesByStatus(HttpServletRequest request, int status){
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return storiesRepository.getUkrStoryItemsByStatus(status);
        else
            return storiesRepository.getEngStoryItemsByStatus(status);
    }

    public List<StoryDTO> getLocaleStoriesByStatusAndUserId(HttpServletRequest request,int status,  Long userid){
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return storiesRepository.getUkrStoryItemsByStatusAndUserId(status, userid);
        else
            return storiesRepository.getEngStoryItemsByStatusAndUserId(status, userid);
    }
    public List<StoryDTO> getLocaleStories(HttpServletRequest request){
        if (RequestContextUtils.getLocale(request).equals(new Locale("ua")))
            return storiesRepository.getUkrStoryItems();
        else
            return storiesRepository.getEngStoryItems();
    }
}
