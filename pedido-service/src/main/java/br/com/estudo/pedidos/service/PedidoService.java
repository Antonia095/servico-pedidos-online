package br.com.estudo.pedidos.service;

import static br.com.estudo.pedidos.enums.StatusPedido.CRIADO;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.kafka.producer.PedidoProducer;
import br.com.estudo.pedidos.model.Pedido;
import br.com.estudo.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

  private final PedidoRepository repository;
  private final PedidoProducer producer;
  private final ObjectMapper objectMapper;

  public Pedido criarPedido(PedidoDTO dto) throws Exception {

    var pedido = Pedido.builder()
        .produto(dto.getProduto())
        .quantidade(dto.getQuantidade())
        .status(CRIADO)
        .build();

    var pedidoSalvo = repository.save(pedido);

    producer.enviarPedido(objectMapper.writeValueAsString(pedidoSalvo));

    return pedidoSalvo;
  }
}
