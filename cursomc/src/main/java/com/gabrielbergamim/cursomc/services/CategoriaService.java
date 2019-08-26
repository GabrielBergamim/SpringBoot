package com.gabrielbergamim.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbergamim.cursomc.domain.Categoria;
import com.gabrielbergamim.cursomc.repositoreis.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElse(null);
	}
}
