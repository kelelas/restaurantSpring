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
    @Column(name = "userid", nullable = false)
    private Long userid;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "dishes_list_ukr", nullable = false)
    private String dishes_list_ukr;
    @Column(name = "dishes_list_eng", nullable = false)
    private String dishes_list_eng;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
}
