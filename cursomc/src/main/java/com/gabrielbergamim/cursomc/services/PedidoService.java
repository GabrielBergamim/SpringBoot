package com.gabrielbergamim.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbergamim.cursomc.domain.ItemPedido;
import com.gabrielbergamim.cursomc.domain.PagamentoComBoleto;
import com.gabrielbergamim.cursomc.domain.Pedido;
import com.gabrielbergamim.cursomc.domain.enums.EstadoPagamento;
import com.gabrielbergamim.cursomc.repositoreis.ItemPedidoRepository;
import com.gabrielbergamim.cursomc.repositoreis.PagamentoRepository;
import com.gabrielbergamim.cursomc.repositoreis.PedidoRepository;
import com.gabrielbergamim.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	@Autowired
	public BoletoService boletoService;
	
	@Autowired
	public PagamentoRepository pagamentoRepository;
	
	@Autowired
	public ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	public ProdutoService produtoService;
	
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
	}
	
}
