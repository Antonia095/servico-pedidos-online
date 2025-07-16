package br.com.estudo.pedidos.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;

  public void enviarPagamentoProcessado(String pedidoJson) {
    kafkaTemplate.send("pagamento-processado", pedidoJson);
  }
}
