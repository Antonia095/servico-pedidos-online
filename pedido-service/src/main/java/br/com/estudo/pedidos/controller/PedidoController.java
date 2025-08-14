package br.com.estudo.pedidos.controller;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.model.Pedido;
import br.com.estudo.pedidos.repository.PedidoRepository;
import br.com.estudo.pedidos.service.PedidoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

  private final PedidoService service;
  private final PedidoRepository repository;

  @PostMapping
  public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) throws Exception {
    var pedidoSalvo = service.criarPedido(pedidoDTO);
    return ResponseEntity.ok(pedidoSalvo);
  }

  @GetMapping
  public ResponseEntity<List<Pedido>> listarPedidos() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
    return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
