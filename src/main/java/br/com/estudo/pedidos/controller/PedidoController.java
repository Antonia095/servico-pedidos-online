package br.com.estudo.pedidos.controller;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.model.Pedido;
import br.com.estudo.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

  private final PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedido) throws Exception {
      Pedido salvo = service.criarPedido(pedido);
      return ResponseEntity.ok(salvo);
    }
}
