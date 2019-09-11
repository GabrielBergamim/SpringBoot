package com.gabrielbergamim.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbergamim.cursomc.domain.Pedido;
import com.gabrielbergamim.cursomc.domain.enums.EstadoPagamento;
import com.gabrielbergamim.cursomc.repositoreis.PedidoRepository;
import com.gabrielbergamim.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
	}
	
}
