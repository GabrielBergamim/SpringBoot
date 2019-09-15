package com.gabrielbergamim.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.gabrielbergamim.cursomc.domain.Cidade;
import com.gabrielbergamim.cursomc.domain.Cliente;
import com.gabrielbergamim.cursomc.domain.Endereco;
import com.gabrielbergamim.cursomc.domain.enums.Perfil;
import com.gabrielbergamim.cursomc.domain.enums.TipoCliente;
import com.gabrielbergamim.cursomc.dto.ClienteDTO;
import com.gabrielbergamim.cursomc.dto.ClienteNewDTO;
import com.gabrielbergamim.cursomc.repositoreis.EnderecoRepository;
import com.gabrielbergamim.cursomc.security.UserSS;
import com.gabrielbergamim.cursomc.services.exceptions.AuthorizationException;
import com.gabrielbergamim.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gabrielbergamim.cursomc.repositoreis.ClienteRepository;
import com.gabrielbergamim.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository repo;

	@Autowired
	public EnderecoRepository enderecoRepository;
	
	@Autowired
	public BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) throws ObjectNotFoundException {

		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		enderecoRepository.saveAll(obj.getEnderecos());
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}

	}

	public List<Cliente> findAll(){
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO){
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO clienteNewDTO){
		Cliente cli = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(clienteNewDTO.getTipo()), pe.encode(clienteNewDTO.getSenha()));
		Cidade cidade = new Cidade(clienteNewDTO.getCidadeID(), null, null);
		Endereco end = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(),
				clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cli, cidade);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(clienteNewDTO.getTelefone());

		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj){
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
	}
}
