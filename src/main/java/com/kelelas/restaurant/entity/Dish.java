package com.kelelas.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="dishes",
        uniqueConstraints={@UniqueConstraint(columnNames={"name_eng"})})
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(nullable = false)
    private String name_eng;
    @Column(name = "name_ukr", nullable = false)
    private String name_ukr;
    @Column(name = "main_ingredient_id", nullable = false)
    private Long main_ingredient_id;
    @Column(name = "off_ingredient_id", nullable = false)
    private Long off_ingredient_id;
    @Column(name = "price", nullable = false)
    private int price;
}
