package com.study.locadora.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(PedidoDeCompraIds.class)
@Entity
public class PedidosDeCompra implements Serializable {
    @Id
    private Integer idPedido;
    @Id
    private Integer idProduto;
    private int quantidade;
    private BigDecimal valor;
}
