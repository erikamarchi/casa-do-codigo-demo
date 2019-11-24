package br.com.caelum.casadocodigo.servlet.autor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Autor;

public class AutorMapper {

	public static Autor mapper(HttpServletRequest request) {

		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String resumo = request.getParameter("resumo");

		Autor autor = new Autor(nome, resumo);
		autor.setId(id != null ? Long.parseLong(id) : 0);	

		return autor;
	}

}
