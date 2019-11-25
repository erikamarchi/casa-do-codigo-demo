package br.com.caelum.casadocodigo.servlet.autor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class AutorMapper {

	public static Autor mapper(HttpServletRequest request) {

		String nome = request.getParameter("nome");
		String resumo = request.getParameter("resumo");

		Autor autor = new Autor(nome, resumo);
		PathResolver.getIdFrom(request)
			.ifPresent(autor::setId);

		return autor;
	}

}
