package com.kelelas.restaurant.repository;


import com.kelelas.restaurant.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    Page<History> findAllByUserId(Long userId, Pageable pageable);
    List<History> findAllByStatusId(Long statusId);
    List<History> findAllByStatusIdAndUserId(Long statusId, Long userId);

}
