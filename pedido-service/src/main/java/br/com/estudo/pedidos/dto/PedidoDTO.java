package br.com.estudo.pedidos.dto;

import br.com.estudo.pedidos.enums.StatusPedido;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PedidoDTO {

   private Long id;
   private String produto;
   private Integer quantidade;
   private StatusPedido status;
   private BigDecimal valor;
}
