package br.com.caelum.casadocodigo.servlet.autor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.QueryParametersResolve;

public class AutorMapper {

	public static Autor mapper(HttpServletRequest request) {

		Long id = QueryParametersResolve.getParametroId(request);
		String nome = request.getParameter("nome");
		String resumo = request.getParameter("resumo");

		Autor autor = new Autor(nome, resumo);
		autor.setId(id);	

		return autor;
	}

}
