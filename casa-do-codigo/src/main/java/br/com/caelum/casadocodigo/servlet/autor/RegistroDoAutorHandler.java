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
import br.com.caelum.casadocodigo.service.AutorService;
import br.com.caelum.casadocodigo.servlet.PathResolver;

public class RegistroDoAutorHandler {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final BiConsumer<AutorService, Autor> onSuccess;
	private final BiConsumer<AutorDto, AutorErrorForm> onFail;

	RegistroDoAutorHandler(HttpServletRequest request, HttpServletResponse response, BiConsumer<AutorService, Autor> onSuccess, BiConsumer<AutorDto, AutorErrorForm> onFail) {
		this.request = request;
		this.response = response;
		this.onSuccess = onSuccess;
		this.onFail = onFail;
	}

	public void execute() throws ServletException, IOException {
		AutorDto autorDto = new AutorDto(request);
		Connection connection = (Connection) request.getAttribute("conexao");
		AutorDao autorDao = new AutorDao(connection);
		
		AutorService autorService = new AutorService(autorDao);
		
		Optional<AutorErrorForm> autorErroFormOptional = new AutorErrorForm.Validator(autorDto).valida(autorService );

		if (autorErroFormOptional.isPresent()) {			
			onFail.accept(autorDto, autorErroFormOptional.get());			
			
			request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
			
		} else {
			onSuccess.accept(autorService, autorDto.toModel());

			response.sendRedirect("/autores");
		}
	}

}
