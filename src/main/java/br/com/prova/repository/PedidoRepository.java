package br.com.prova.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
	
}
