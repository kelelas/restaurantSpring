package com.kelelas.restaurant.service;

import com.kelelas.restaurant.config.ConstantBundle;
import com.kelelas.restaurant.dto.BillDTO;
import com.kelelas.restaurant.entity.Bill;
import com.kelelas.restaurant.entity.History;
import com.kelelas.restaurant.exception.DBException;
import com.kelelas.restaurant.mapper.LocaleBillMapper;
import com.kelelas.restaurant.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BillService {
BillRepository billRepository;
LocaleBillMapper mapper;

@Autowired
    public BillService(BillRepository billRepository, LocaleBillMapper mapper) {
        this.billRepository = billRepository;
        this.mapper = mapper;
    }

    public void saveNewBill(History history){
        Bill bill = Bill.builder()
                .id(history.getId())
                .date(LocalDateTime.now())
                .price(history.getPrice())
                .dishes(history.getDishes())
                .statusId((long) ConstantBundle.getIntProperty("status.waitingForPay"))
                .userId(history.getUserId())
                .build();
        billRepository.save(bill);
    }

    public void update(Bill bill){
        bill.setDate(LocalDateTime.now());
        billRepository.save(bill);}

    public List<BillDTO> getLocaleBillsByStatusAndUserId( Long status, Long userId){
    return billRepository.getAllByStatusIdAndUserId(status, userId).stream().map(mapper :: dtoMapper).collect(Collectors.toList());
    }

    public Bill getBillById(Long id){
        return billRepository.findById(id).orElseThrow(DBException::new);
    }
}
