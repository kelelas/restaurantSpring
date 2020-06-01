package com.kelelas.restaurant.repository;

import com.kelelas.restaurant.dto.BillDTO;
import com.kelelas.restaurant.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> getAllByStatusIdAndUserId(Long status, Long userId);
}
