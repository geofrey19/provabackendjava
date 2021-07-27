package br.com.prova.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.prova.model.PedidoItem;
import br.com.prova.services.PedidoItemService;

@RestController
@RequestMapping(value = "/pedidoitem")
public class PedidoItemResource {

	@Autowired
	public PedidoItemService service;
	
	@GetMapping(value = "listatodos")
	@ResponseBody
	public ResponseEntity<List<PedidoItem>> findAll(){
		List<PedidoItem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoItem> findById(@PathVariable UUID id){
		PedidoItem PedidoItem = service.findById(id);
		return ResponseEntity.ok().body(PedidoItem);
	}
	
	@PostMapping(value = "/salva")
	public ResponseEntity<PedidoItem> insert(@RequestBody PedidoItem pedidoItem){
		
		service.insert(pedidoItem);
		//pedidoItem = service.insert(pedidoItem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedidoItem.getPedido().getId()).toUri();
		return ResponseEntity.created(uri).body(pedidoItem);
	}
}
