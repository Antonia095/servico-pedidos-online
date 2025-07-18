package br.com.estudo.pagamento.kafka.consumer;

import static br.com.estudo.pagamento.enums.StatusPedido.PAGO;

import br.com.estudo.pagamento.dto.PedidoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoConsumer {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "novo-pedido", groupId = "pagamento")
  public void consumirNovoPedido(String mensagem) throws Exception {

    var pedidoDTO = objectMapper.readValue(mensagem, PedidoDTO.class);
    pedidoDTO.setStatus(PAGO);

    kafkaTemplate.send("pagamento-processado", objectMapper.writeValueAsString(pedidoDTO));
  }
}
