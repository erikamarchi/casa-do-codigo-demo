package br.com.caelum.casadocodigo.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Livro {
	
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(new Locale("pt", "BR"));
	
	private Long id;
	private String titulo;
	private Long idDaCategoria;
	private String nomeDaCategoria;
	private Double preco;
	private String resumo;
	private Long idDoAutor;
	private String nomeDoAutor;
	private Integer numeroDePaginas;
	private String isbn;
	
	public Livro(Long id, String titulo, Long idDaCategoria, String nomeDaCategoria, Double preco, String resumo,
			Long idDoAutor, String nomeDoAutor, Integer numeroDePaginas, String isbn) {
		this.id = id;
		this.titulo = titulo;
		this.idDaCategoria = idDaCategoria;
		this.nomeDaCategoria = nomeDaCategoria;
		this.preco = preco;
		this.resumo = resumo;
		this.idDoAutor = idDoAutor;
		this.nomeDoAutor = nomeDoAutor;
		this.numeroDePaginas = numeroDePaginas;
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Long getIdDaCategoria() {
		return idDaCategoria;
	}

	public String getNomeDaCategoria() {
		return nomeDaCategoria;
	}

	public Double getPreco() {
		return preco;
	}

	public String getPrecoFormatado() {
		return NUMBER_FORMAT.format(getPreco());
	}

	public String getResumo() {
		return resumo;
	}

	public Long getIdDoAutor() {
		return idDoAutor;
	}

	public String getNomeDoAutor() {
		return nomeDoAutor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public String getIsbn() {
		return isbn;
	}
	
}
