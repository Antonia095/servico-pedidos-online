package br.com.estudo.pedidos.dto;

import lombok.Data;

@Data
public class PedidoDTO {

   private Long id;
   private String produto;
   private Integer quantidade;
   private String status;
}
