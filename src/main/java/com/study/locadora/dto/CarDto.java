package com.study.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotBlank(message = "A placa nao pode estar vazia. Preencha!!!!!!!!")
    @Size(min = 3)
    private String placa;

    @NotBlank   //string nao pode ser vazia e nem nula
    @Size(min = 3) //string tem que ter tamanho minimo 3
    private String descricao;

    @Min(2000)
    @Max(2024)
    @NotNull
    private int ano;

}
