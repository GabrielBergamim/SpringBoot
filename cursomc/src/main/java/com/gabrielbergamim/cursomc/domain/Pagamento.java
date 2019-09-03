package com.gabrielbergamim.cursomc.domain;

import com.gabrielbergamim.cursomc.domain.enums.EstadoPagamento;

public class Pagamento {
	private Integer id;
	private EstadoPagamento estado;
	
	private Pedido pedido;
	
	public Pagamento() {
		// TODO Auto-generated constructor stub
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado;
		this.pedido = pedido;
	}
	
	
	
}
