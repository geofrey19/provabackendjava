package br.com.prova.model.enums;

/*No cadastro de produto/serviço deverá ter uma indicação para diferenciar um produto de um serviço*/
public enum ProdutoServicoTipo {

	PRODUTO(0),
	SERVIÇO(1);
	
	private int cod;
	
	private ProdutoServicoTipo(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public static ProdutoServicoTipo valueOf(int cod) {
		for (ProdutoServicoTipo value : ProdutoServicoTipo.values()) {
			if(value.getCod() == cod) {
				return value;
			}
			
		}
		throw new IllegalArgumentException("Tipo inválido");
	}
}
