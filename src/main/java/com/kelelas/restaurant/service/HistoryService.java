package com.kelelas.restaurant.service;



import com.kelelas.restaurant.dto.HistoryDTO;
import com.kelelas.restaurant.entity.History;
import com.kelelas.restaurant.exception.DBException;
import com.kelelas.restaurant.mapper.LocaleHistoryMapper;
import com.kelelas.restaurant.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class HistoryService {
    HistoryRepository historyRepository;
    LocaleHistoryMapper mapper;
    @Autowired
    public HistoryService(HistoryRepository historyRepository, LocaleHistoryMapper mapper) {
        this.historyRepository = historyRepository;
        this.mapper = mapper;
    }

    public History getStoryById(Long id){
        return historyRepository.findById(id).orElseThrow(DBException::new);
    }

    public void save(History history){
        historyRepository.save(history);
    }

    public void update(History history){
        history.setDate(LocalDateTime.now());
        historyRepository.save(history);}

    public Page<HistoryDTO> getLocaleStoriesByUserId(Long userId, Pageable pageable){
        return historyRepository.findAllByUserId(userId, pageable)
                .map(mapper::dtoMapper);
    }

    public List<HistoryDTO> getLocaleStoriesByStatus(Long statusId){
        return historyRepository.findAllByStatusId(statusId).stream().map(mapper::dtoMapper)
                .sorted(Comparator.comparingLong(HistoryDTO::getId))
                .collect(Collectors.toList());   }

        public Page<HistoryDTO> getLocaleStories(Pageable pageable){
        return historyRepository.findAll(pageable)
                .map(mapper::dtoMapper);   }


}
