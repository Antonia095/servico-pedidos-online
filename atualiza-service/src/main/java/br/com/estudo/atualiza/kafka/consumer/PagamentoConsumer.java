package br.com.estudo.atualiza.kafka.consumer;

import br.com.estudo.atualiza.dto.PedidoDTO;
import br.com.estudo.atualiza.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoConsumer {

  private final PedidoRepository repository;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "pagamento-processado", groupId = "status")
  public void consumirPagamentoProcessado(String mensagem) throws Exception {

    var pedidoDTO = objectMapper.readValue(mensagem, PedidoDTO.class);

    repository.findById(pedidoDTO.getId()).ifPresent(pedido -> {
      pedido.setStatus(pedidoDTO.getStatus());
      repository.save(pedido);
    });
  }
}
