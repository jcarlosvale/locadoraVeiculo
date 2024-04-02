package com.study.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Carro")
public class Car {

    @Id
    private String placa;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer ano;

}
