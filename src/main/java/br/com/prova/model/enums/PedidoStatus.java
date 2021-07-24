package br.com.prova.model.enums;

public enum PedidoStatus {

	ABERTO(0),
	FECHADO(1);
	
	private int cod;
	
	private PedidoStatus(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public static PedidoStatus valueOf(int cod) {
		for (PedidoStatus value : PedidoStatus.values()) {
			if(value.getCod() == cod) {
				return value;
			}
			
		}
		throw new IllegalArgumentException("Status inválido");
	}
	
}
