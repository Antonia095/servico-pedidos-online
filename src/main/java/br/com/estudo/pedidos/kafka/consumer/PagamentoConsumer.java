package br.com.estudo.pedidos.kafka.consumer;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.repository.PedidoRepository;
import br.com.estudo.pedidos.service.PedidoAtualizadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoConsumer {

  private final PedidoAtualizadoService pedidoAtualizadoService;
  private final ObjectMapper objectMapper;


  @KafkaListener(topics = "pagamento-processado", groupId = "status")
  public void consumirPagamentoProcessado(String mensagem) throws Exception {

    var pedidoDTO = objectMapper.readValue(mensagem, PedidoDTO.class);

    pedidoAtualizadoService.atualizarStatusPedido(pedidoDTO);
  }
}
