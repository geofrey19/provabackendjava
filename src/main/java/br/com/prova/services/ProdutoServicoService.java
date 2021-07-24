package br.com.prova.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prova.model.ProdutoServico;
import br.com.prova.repository.ProdutoServicoRepository;

@Service
public class ProdutoServicoService {

	@Autowired
	private ProdutoServicoRepository repository;
	
	public List<ProdutoServico> findAll(){
		return repository.findAll();
	}
	
	public ProdutoServico findById(UUID id) {
		Optional<ProdutoServico> ProdutoServico = repository.findById(id);
		return ProdutoServico.get();
		
	}
	
	public ProdutoServico insert(ProdutoServico prodserv) {
		return repository.save(prodserv);
	}
}
