package com.gabrielbergamim.cursomc.resources;

import com.gabrielbergamim.cursomc.domain.Produto;

import com.gabrielbergamim.cursomc.dto.ProdutoDTO;
import com.gabrielbergamim.cursomc.resources.utils.URL;
import com.gabrielbergamim.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		Produto obj;
		obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value="nome", defaultValue = "")String nome,
												 @RequestParam(value="categorias", defaultValue = "0")String categorias,
			  									 @RequestParam(value="page", defaultValue = "0")Integer page,
												 @RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage,
												 @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
												 @RequestParam(value="direction", defaultValue = "ASC") String direction) {

		List<Integer> ids = URL.decodIntegerList(categorias);
		String nomeDecode = URL.decoParam(nome);
		Page<Produto> list;
		list = service.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);

		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listDto);
	}
	
}
