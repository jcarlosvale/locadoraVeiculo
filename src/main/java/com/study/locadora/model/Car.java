package com.study.locadora.model;

import com.study.locadora.domain.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Carro")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private String placa;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int ano;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoria;

}
