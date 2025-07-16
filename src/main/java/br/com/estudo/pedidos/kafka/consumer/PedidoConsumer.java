package br.com.estudo.pedidos.kafka.consumer;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.service.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoConsumer {

  private final PagamentoService pagamentoService;
  private final ObjectMapper objectMapper;


  @KafkaListener(topics = "novo-pedido", groupId = "pagamento")
  public void consumirNovoPedido(String mensagem) throws Exception {
    var pedido = objectMapper.readValue(mensagem, PedidoDTO.class);
    // Simula processamento de pagamento
    pagamentoService.processarPagamento(pedido);
  }
}
