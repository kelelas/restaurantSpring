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
@Table( name="status",
        uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Status {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "status_eng", nullable = false)
    private String statusEng;
    @Column(name = "status_ukr", nullable = false)
    private String statusUkr;
}
