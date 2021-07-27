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

import br.com.prova.model.ProdutoServico;
import br.com.prova.repository.ProdutoServicoRepository;
import br.com.prova.services.exceptions.DataBaseException;
import br.com.prova.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoServicoService {

	@Autowired
	private ProdutoServicoRepository repository;

	public List<ProdutoServico> findAll() {
		return repository.findAll();
	}

	public ProdutoServico findById(UUID id) {
		try {
			Optional<ProdutoServico> produtoServico = repository.findById(id);
			return produtoServico.orElseThrow(() -> new ResourceNotFoundException(id));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public ProdutoServico insert(ProdutoServico prodserv ) {
		try {
			return repository.save(prodserv);	
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
		
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

	public ProdutoServico update(UUID id, ProdutoServico prodserv) {
		try {
			ProdutoServico entidade = repository.getById(id);
			updateData(entidade, prodserv);
			return repository.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (ConstraintViolationException e) {
			throw new DataBaseException(e.getMessage());
		}

	}

	private void updateData(ProdutoServico entidade, ProdutoServico prodserv) {
		entidade.setNome(prodserv.getNome());
		entidade.setPreco(prodserv.getPreco());

	}
}
