package com.kelelas.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="ingredients",
        uniqueConstraints={@UniqueConstraint(columnNames={"name_eng"})})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name_eng",nullable = false)
    private String nameEng;
    @Column(name = "name_ukr", nullable = false)
    private String nameUkr;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "max_amount", nullable = false)
    private int maxAmount;

}
