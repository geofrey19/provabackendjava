package br.com.prova.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.model.PedidoItem;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID> {
	
}
