package br.com.prova.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.model.ProdutoServico;
import br.com.prova.services.ProdutoServicoService;

@RestController
@RequestMapping(value = "/produtoservico")
public class ProdutoServicoResource {

	@Autowired
	public ProdutoServicoService service;
	
	@GetMapping
	public ResponseEntity<List<ProdutoServico>> findAll(){
		List<ProdutoServico> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoServico> findById(@PathVariable UUID id){
		ProdutoServico pedido = service.findById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoServico> insert(@RequestBody ProdutoServico prodserv){
		prodserv = service.insert(prodserv);
		return ResponseEntity.ok().body(prodserv);
	}
	
}