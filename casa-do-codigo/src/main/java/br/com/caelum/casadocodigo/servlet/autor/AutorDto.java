package br.com.caelum.casadocodigo.servlet.autor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class AutorDto {

	private Long id;
	private String nome;
	private String resumo;
	
	AutorDto (HttpServletRequest request) {
		this.nome = request.getParameter("nome");
		this.resumo = request.getParameter( "resumo");		
		PathResolver.getIdFrom(request)
			.ifPresent(requestId -> this.id = requestId);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getResumo() {
		return resumo;
	}

	Autor toModel() {
		Autor model = new Autor(id, nome, resumo);
		return model;
	}
}
