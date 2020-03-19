package com.kelelas.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="history",
        uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class HistoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( nullable = false)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long user_id;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "dishes_list", nullable = false)
    private String dishes_list;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
