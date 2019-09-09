package com.gabrielbergamim.cursomc.resources;

import com.gabrielbergamim.cursomc.domain.Categoria;
import com.gabrielbergamim.cursomc.domain.Cliente;
import com.gabrielbergamim.cursomc.dto.CategoriaDTO;
import com.gabrielbergamim.cursomc.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gabrielbergamim.cursomc.services.ClienteService;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj;
		obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		Cliente obj;
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> find(@RequestParam(value="page", defaultValue = "0")Integer page,
													@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage,
													@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
												    @RequestParam(value="direction", defaultValue = "ASC") String direction) {

		Page<Cliente> list;
		list = service.findPage(page, linesPerPage, orderBy, direction);

		Page<ClienteDTO> listDto = list.map(obj-> new ClienteDTO(obj));

		return ResponseEntity.ok().body(listDto);
	}
}

