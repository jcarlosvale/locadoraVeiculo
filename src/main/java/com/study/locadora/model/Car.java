package com.study.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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

    @OneToMany(mappedBy = "carro")
    private List<Rent> alugueis;

}
