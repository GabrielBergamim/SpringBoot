package com.gabrielbergamim.cursomc.domain;

import java.util.Date;

import com.gabrielbergamim.cursomc.domain.enums.EstadoPagamento;

public class PagamentoComBoleto extends Pagamento{
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		// TODO Auto-generated constructor stub
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
	
	
	
}
