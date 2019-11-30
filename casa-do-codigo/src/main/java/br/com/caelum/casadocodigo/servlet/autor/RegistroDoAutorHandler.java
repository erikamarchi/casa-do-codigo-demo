package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;
import java.util.function.BiConsumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class RegistroDoAutorHandler {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final BiConsumer<AutorDao, Autor> onSuccess;

	RegistroDoAutorHandler(HttpServletRequest request, HttpServletResponse response, BiConsumer<AutorDao, Autor> onSuccess) {
		this.request = request;
		this.response = response;
		this.onSuccess = onSuccess;
	}

	public void execute() throws ServletException, IOException {
		AutorDto autorDto = new AutorDto(request);
		Connection connection = (Connection) request.getAttribute("conexao");
		AutorDao autorDao = new AutorDao(connection);
		Optional<AutorErrorForm> autorErroFormOptional = new AutorErrorForm.Validator(autorDto).valida(autorDao);

		if (autorErroFormOptional.isPresent()) {
			request.setAttribute("autor", autorDto);
			request.setAttribute("autorError", autorErroFormOptional.get());
			request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
		} else {
			onSuccess.accept(autorDao, autorDto.toModel());

			response.sendRedirect("/autores");
		}
	}

}
