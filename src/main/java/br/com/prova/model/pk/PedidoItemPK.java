package br.com.prova.model.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.prova.model.Pedido;
import br.com.prova.model.ProdutoServico;

@Embeddable
public class PedidoItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "prodserv_id")
	private ProdutoServico prodserv;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public ProdutoServico getProdServ() {
		return prodserv;
	}
	public void setProdServ(ProdutoServico prodserv) {
		this.prodserv = prodserv;
	}
	@Override
	public int hashCode() {
		return Objects.hash(pedido, prodserv);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItemPK other = (PedidoItemPK) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(prodserv, other.prodserv);
	}
	
}
