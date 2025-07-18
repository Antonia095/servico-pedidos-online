package br.com.estudo.atualiza.repository;

import br.com.estudo.atualiza.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
