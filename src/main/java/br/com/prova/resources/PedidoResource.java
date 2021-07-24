package br.com.prova.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.model.Pedido;
import br.com.prova.services.PedidoService;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

	@Autowired
	public PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	public ResponseEntity<Pedido> findById(@PathVariable UUID id){
		Pedido pedido = service.findById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
}
