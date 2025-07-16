package br.com.estudo.pedidos.service;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.kafka.producer.PagamentoProducer;
import br.com.estudo.pedidos.model.Pedido;
import br.com.estudo.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

  private final PedidoRepository repository;
  private final PagamentoProducer producer;
  private final ObjectMapper objectMapper;

  public void processarPagamento(PedidoDTO dto) throws Exception {

    var pedido = Pedido.builder()
        .id(dto.getId())
        .produto(dto.getProduto())
        .quantidade(dto.getQuantidade())
        .status("PAGO")
        .build();
    repository.save(pedido);
    producer.enviarPagamentoProcessado(objectMapper.writeValueAsString(pedido));
  }
}