package com.gabrielbergamim.cursomc.domain;

import java.util.Date;

public class Pedido {
	private Integer id;
	private Date instante;
	private Pagamento pagamento;
	
	private Endereco enderecoDeEntrega;
	private Cliente cliente;
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, Date instante, Pagamento pagamento, Endereco enderecoDeEntrega, Cliente cliente) {
		super();
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.cliente = cliente;
	}
	
	
	
}
