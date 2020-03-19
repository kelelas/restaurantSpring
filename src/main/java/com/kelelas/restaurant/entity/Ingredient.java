package com.kelelas.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="ingredient",
        uniqueConstraints={@UniqueConstraint(columnNames={"name_eng"})})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name_eng;
    @Column(name = "name_ukr", nullable = false)
    private String name_ukr;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "max_amount", nullable = false)
    private int max_amount;
}
