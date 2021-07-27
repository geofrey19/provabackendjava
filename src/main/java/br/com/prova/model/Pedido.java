package br.com.prova.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.prova.model.enums.PedidoStatus;
import br.com.prova.model.enums.ProdutoServicoTipo;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	/*Todos as entidades deverão ter um ID único do tipo UUID gerado automaticamente*/
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@FutureOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private  Instant momento;
	
	@NotNull
	private Integer status;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<PedidoItem> items = new HashSet<>(); 

	/*Cascade mapeia com o mesmo id*/
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	private Double desconto;
	
	public Pedido() {
		
	}

	public Pedido(UUID id, Instant momento, PedidoStatus status, Double desconto ) {
		super();
		this.id = id;
		this.momento = momento;
		setStatus(status);
		this.desconto = desconto;
	}

	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}
	
	
	public PedidoStatus getStatus() {
		return PedidoStatus.valueOf(status);
	}

	public void setStatus(PedidoStatus status) {
		if (status !=null) {
			this.status = status.getCod();
		}
		
	}
			
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Set<PedidoItem> getItems() {
		return items;
	}
		
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getTotal() {
		double soma = 0.0;
		double produto = 0.0;
		double servico = 0.0;
		for (PedidoItem pedidoItem : items) {
			/*Deverá ser possível aplicar um percentual de desconto no pedido, porém apenas para os
			itens que sejam produto (não serviço); o desconto será sobre o valor total dos produtos*/
			if(pedidoItem.getProdServ().getTipo() == ProdutoServicoTipo.PRODUTO ) {
				produto = produto + pedidoItem.getSubTotal();
			}else {
				servico = servico + pedidoItem.getSubTotal();
			}
		}
		produto = produto -((getDesconto() * produto)/100);
		soma = soma + produto + servico;
		return soma;
	}//produto = produto + (pedidoItem.getSubTotal() - (pedidoItem.getDesconto() * pedidoItem.getSubTotal())/100);
	
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
