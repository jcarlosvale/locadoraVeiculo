package com.study.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDeCompraIds implements Serializable {
    private Car car;
    private Customer idProduto;
}
