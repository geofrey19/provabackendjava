 package br.com.prova.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.prova.model.Pedido;
import br.com.prova.repository.PedidoRepository;
import br.com.prova.services.exceptions.DataBaseException;
import br.com.prova.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(UUID id) {
		try {
			Optional<Pedido> pedido = repository.findById(id);
			return pedido.orElseThrow(() -> new ResourceNotFoundException(id));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

	}

	public Pedido insert(Pedido pedido) {
		return repository.save(pedido);
	}
	
	public void delete(UUID id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Pedido update(UUID id, Pedido pedido) {
		try {
			Pedido entidade = repository.getById(id);
			updateData(entidade, pedido);
			return repository.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (ConstraintViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

	}

	private void updateData(Pedido entidade, Pedido pedido) {
		entidade.setStatus(pedido.getStatus());
		entidade.setPagamento(pedido.getPagamento());
		entidade.setMomento(pedido.getMomento());
	}
	
	
}
