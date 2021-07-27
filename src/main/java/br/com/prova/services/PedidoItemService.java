package br.com.prova.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.prova.model.PedidoItem;
import br.com.prova.repository.PedidoItemRepository;
import br.com.prova.services.exceptions.DataBaseException;
import br.com.prova.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoItemService {

	@Autowired
	private PedidoItemRepository repository;

	public List<PedidoItem> findAll() {
		return repository.findAll();
	}

	public PedidoItem findById(UUID id) {
		try {
			Optional<PedidoItem> PedidoItem = repository.findById(id);
			return PedidoItem.orElseThrow(() -> new ResourceNotFoundException(id));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

	}

	public PedidoItem insert(PedidoItem pedidoItem) {
		try {
			return repository.save(pedidoItem);	
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
		
	}
}
