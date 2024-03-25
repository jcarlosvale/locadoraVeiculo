package com.study.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @NotBlank(message = "placa nao pode estar vazia")
    private String placa;

    @NotBlank(message = "descicao nao pode estar vazia")
    @Size(min = 2)
    private String descricao;

    @Min(2000)
    private int ano;

}
