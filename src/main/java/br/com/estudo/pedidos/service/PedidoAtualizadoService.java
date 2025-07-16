package br.com.estudo.pedidos.service;

import br.com.estudo.pedidos.dto.PedidoDTO;
import br.com.estudo.pedidos.model.Pedido;
import br.com.estudo.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoAtualizadoService {

  private final PedidoRepository repository;

  public void atualizarStatusPedido(PedidoDTO dto) {
    repository.findById(dto.getId()).ifPresentOrElse(
        pedido -> {
          pedido.setStatus(dto.getStatus());
          repository.save(pedido);
        },
        () -> {
          var novo = Pedido.builder()
              .id(dto.getId())
              .produto(dto.getProduto())
              .quantidade(dto.getQuantidade())
              .status(dto.getStatus())
              .build();
          repository.save(novo);
        }
    );
  }

}
