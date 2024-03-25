package com.study.locadora.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.locadora.domain.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    @NotBlank(message = "placa nao pode estar vazia")
    private String placa;

    @NotBlank(message = "descicao nao pode estar vazia")
    @Size(min = 2)
    private String descricao;

    @Min(2000)
    @NotNull
    private int ano;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private CategoryEnum categoria;

}
