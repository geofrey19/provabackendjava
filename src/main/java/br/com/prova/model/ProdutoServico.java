package br.com.prova.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.prova.model.enums.ProdutoServicoTipo;

@Entity
public class ProdutoServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	private String nome;
	private Double preco;
	private Integer tipo; // Produto ou Serviço

	@OneToMany(mappedBy = "id.prodserv")
	private Set<PedidoItem> items = new HashSet<>();
	
	public ProdutoServico() {
		
	}
	
	public ProdutoServico(UUID id, String nome, Double preco, ProdutoServicoTipo tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		setTipo(tipo);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ProdutoServicoTipo getTipo() {
		return ProdutoServicoTipo.valueOf(tipo);
	}

	public void setTipo(ProdutoServicoTipo tipo) {
		if (tipo != null) {
			this.tipo = tipo.getCod();
		}
	}
	
	@JsonIgnore
	public Set<Pedido> getPedido(){
		Set<Pedido> set = new HashSet<>();
		for(PedidoItem pedidoItem : items) {
			set.add(pedidoItem.getPedido());
		}
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoServico other = (ProdutoServico) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
