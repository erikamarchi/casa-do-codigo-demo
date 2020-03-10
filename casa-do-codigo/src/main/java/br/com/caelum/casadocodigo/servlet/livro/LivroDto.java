package br.com.caelum.casadocodigo.servlet.livro;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Livro;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class LivroDto {
	
	private Long id;
	private String titulo;
	private String idDaCategoria;
	private String preco;
	private String resumo;
	private String idDoAutor;
	private String numeroDePaginas;
	private String isbn;
	
	LivroDto (HttpServletRequest request) {
		this.titulo = request.getParameter("titulo");
		this.idDaCategoria = request.getParameter("idDaCategoria");
		this.preco = request.getParameter("preco");
		this.resumo = request.getParameter("resumo");
		this.idDoAutor = request.getParameter("idDoAutor");
		this.numeroDePaginas = request.getParameter("numeroDePaginas");
		this.isbn = request.getParameter("isbn");
		PathResolver.getIdFrom(request)
			.ifPresent(requestId -> this.id = requestId);
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getIdDaCategoria() {
		return idDaCategoria;
	}

	public String getPreco() {
		return preco;
	}

	public String getPrecoFormatado() {
		return preco == null ? null : preco.replaceAll("\\.", "").replaceAll(",", ".");
	}

	public String getResumo() {
		return resumo;
	}

	public String getIdDoAutor() {
		return idDoAutor;
	}

	public String getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	Livro toModel() {
		return new Livro(id, titulo, Long.parseLong(idDaCategoria), null, Double.parseDouble(getPrecoFormatado()), resumo, Long.parseLong(idDoAutor), null,
				Integer.valueOf(numeroDePaginas), isbn);
	}

}
