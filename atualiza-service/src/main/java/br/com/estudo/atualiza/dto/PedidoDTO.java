package br.com.estudo.atualiza.dto;

import br.com.estudo.atualiza.enums.StatusPedido;
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
