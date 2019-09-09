package com.gabrielbergamim.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielbergamim.cursomc.domain.Categoria;
import com.gabrielbergamim.cursomc.dto.CategoriaDTO;
import com.gabrielbergamim.cursomc.services.CategoriaService;

import javax.validation.Valid;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj;
		obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		Categoria obj;
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> find(@RequestParam(value="page", defaultValue = "0")Integer page,
													@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage,
													@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
												    @RequestParam(value="direction", defaultValue = "ASC") String direction) {

		Page<Categoria> list;
		list = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoriaDTO> listDto = list.map(obj-> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
	
}
