package com.kelelas.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "name_eng", nullable = false)
    private String nameEng;
    @Column(name = "name_ukr", nullable = false)
    private String nameUkr;
    @Column(name = "price", nullable = false)
    private int price;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "dishes_ingredients",
            joinColumns = {
                    @JoinColumn(name = "dishes_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ingredients_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Ingredient> ingredients = new ArrayList<>();
}
