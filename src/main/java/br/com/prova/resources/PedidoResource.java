package br.com.prova.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.prova.model.Pedido;
import br.com.prova.services.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

	@Autowired
	public PedidoService service;
	
	@GetMapping(value = "listatodos")
	@ResponseBody
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable UUID id){
		Pedido pedido = service.findById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping(value = "/salva")
	public ResponseEntity<Pedido> insert(@RequestBody Pedido pedido){
		pedido = service.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(pedido);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pedido> update(@PathVariable UUID id, @RequestBody Pedido pedido){
		pedido = service.update(id, pedido);
		return ResponseEntity.ok().body(pedido);
	}
	
}
