package com.projeto.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projeto.cursomc.domain.Categoria;
import com.projeto.cursomc.domain.Cidade;
import com.projeto.cursomc.domain.Cliente;
import com.projeto.cursomc.domain.Endereco;
import com.projeto.cursomc.domain.Estado;
import com.projeto.cursomc.domain.Produto;
import com.projeto.cursomc.domain.enums.TipoCliente;
import com.projeto.cursomc.repositories.CategoriaRepository;
import com.projeto.cursomc.repositories.CidadeRepository;
import com.projeto.cursomc.repositories.ClienteRepository;
import com.projeto.cursomc.repositories.EnderecoRepository;
import com.projeto.cursomc.repositories.EstadoRepository;
import com.projeto.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escrtório");

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

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1));

	}

}
