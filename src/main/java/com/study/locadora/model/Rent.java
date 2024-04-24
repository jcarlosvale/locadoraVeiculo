package com.study.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Aluguel")
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer locatario;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Car carro;

    private ZonedDateTime dataAluguel;
}
