package br.com.estudo.pedidos.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void enviarPedido(String pedidoJson) {
    kafkaTemplate.send("novo-pedido", pedidoJson);
  }
}