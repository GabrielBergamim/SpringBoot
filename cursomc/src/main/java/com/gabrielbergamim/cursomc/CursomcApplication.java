package com.gabrielbergamim.cursomc;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielbergamim.cursomc.domain.Categoria;
import com.gabrielbergamim.cursomc.domain.Cidade;
import com.gabrielbergamim.cursomc.domain.Cliente;
import com.gabrielbergamim.cursomc.domain.Endereco;
import com.gabrielbergamim.cursomc.domain.Estado;
import com.gabrielbergamim.cursomc.domain.ItemPedido;
import com.gabrielbergamim.cursomc.domain.Pagamento;
import com.gabrielbergamim.cursomc.domain.PagamentoComBoleto;
import com.gabrielbergamim.cursomc.domain.PagamentoComCartao;
import com.gabrielbergamim.cursomc.domain.Pedido;
import com.gabrielbergamim.cursomc.domain.Produto;
import com.gabrielbergamim.cursomc.domain.enums.EstadoPagamento;
import com.gabrielbergamim.cursomc.domain.enums.TipoCliente;
import com.gabrielbergamim.cursomc.repositoreis.CategoriaRepository;
import com.gabrielbergamim.cursomc.repositoreis.CidadeRepository;
import com.gabrielbergamim.cursomc.repositoreis.ClienteRepository;
import com.gabrielbergamim.cursomc.repositoreis.EnderecoRepository;
import com.gabrielbergamim.cursomc.repositoreis.EstadoRepository;
import com.gabrielbergamim.cursomc.repositoreis.ItemPedidoRepository;
import com.gabrielbergamim.cursomc.repositoreis.PagamentoRepository;
import com.gabrielbergamim.cursomc.repositoreis.PedidoRepository;
import com.gabrielbergamim.cursomc.repositoreis.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail", "12354", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("46546", "31658721"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "3000", "Apto 303", "Jardim", "2659", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida", "105", "Sala", "Centro", "2325436", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2, cli1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 2000.00, 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 80.00, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 800.00, 1);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		System.out.println("FM, I'm in hell!!");
		
	}
	
	

}
