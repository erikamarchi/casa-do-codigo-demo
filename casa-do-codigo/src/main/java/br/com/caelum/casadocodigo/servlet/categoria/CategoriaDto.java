package br.com.caelum.casadocodigo.servlet.categoria;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Categoria;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class CategoriaDto {
	
	private Long id;
	private String nome;
	
	CategoriaDto (HttpServletRequest request) {
		this.nome = request.getParameter("nome");			
		PathResolver.getIdFrom(request)
			.ifPresent(requestId -> this.id = requestId);
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	Categoria toModel() {
		Categoria model = new Categoria(id, nome);
		return model;
	}

}
