package br.com.caelum.casadocodigo.servlet.autor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class AutorMapper {

	public static Autor mapper(HttpServletRequest request) {
		
		String nome = validaParametro(request, "nome");
		String resumo = validaParametro(request, "resumo");

		Autor autor = new Autor(nome, resumo);
		PathResolver.getIdFrom(request)
			.ifPresent(autor::setId);

		return autor;
	}
	
	private static String validaParametro(HttpServletRequest request, String nomeDoCampo) {
		//TODO: Ainda não é capturado esse erro para ser mostrado para o usuário
		return Optional
				.ofNullable(request.getParameter(nomeDoCampo))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.orElseThrow(() -> new IllegalArgumentException(
					String.format("Faltou alguma coisa!, você precisa informar o ", nomeDoCampo)
			));	
	}

}
