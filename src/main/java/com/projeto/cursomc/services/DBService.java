package com.projeto.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.cursomc.domain.Categoria;
import com.projeto.cursomc.domain.Cidade;
import com.projeto.cursomc.domain.Cliente;
import com.projeto.cursomc.domain.Endereco;
import com.projeto.cursomc.domain.Estado;
import com.projeto.cursomc.domain.ItemPedido;
import com.projeto.cursomc.domain.Pagamento;
import com.projeto.cursomc.domain.PagamentoComCartao;
import com.projeto.cursomc.domain.Pedido;
import com.projeto.cursomc.domain.Produto;
import com.projeto.cursomc.domain.enums.EstadoPagamento;
import com.projeto.cursomc.domain.enums.TipoCliente;
import com.projeto.cursomc.repositories.CategoriaRepository;
import com.projeto.cursomc.repositories.CidadeRepository;
import com.projeto.cursomc.repositories.ClienteRepository;
import com.projeto.cursomc.repositories.EnderecoRepository;
import com.projeto.cursomc.repositories.EstadoRepository;
import com.projeto.cursomc.repositories.ItemPedidoRepository;
import com.projeto.cursomc.repositories.PagamentoRepository;
import com.projeto.cursomc.repositories.PedidoRepository;
import com.projeto.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");
		Categoria cat3 = new Categoria(null, "Cadeira");
		Categoria cat4 = new Categoria(null, "Estofado");
		Categoria cat5 = new Categoria(null, "Celulare");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Jardinagem");
		Categoria cat9 = new Categoria(null, "Pintura");
		Categoria cat10 = new Categoria(null, "Eletro");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Rio Grande do Sul");
		Estado est2 = new Estado(null, "Santa Catarina");

		Cidade c1 = new Cidade(null, "Porto Alegre", est1);
		Cidade c2 = new Cidade(null, "Chapecó", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "54654564", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("545645646", "656465465"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 502", "Jardim", "12311545", cli1, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		cli1.getPedidos().addAll(Arrays.asList(ped1));

		pedidoRepository.saveAll(Arrays.asList(ped1));
		pagamentoRepository.saveAll(Arrays.asList(pagto1));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 250.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));

		p1.getItens().addAll(Arrays.asList(ip1));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2));
	}

}
