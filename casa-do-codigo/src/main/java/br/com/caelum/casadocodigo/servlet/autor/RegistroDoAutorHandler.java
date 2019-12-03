package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.PathResolver;
import br.com.caelum.casadocodigo.servlet.autor.AutorErrorForm.Validator;

public class RegistroDoAutorHandler {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final Validator validator;
	private final Consumer<Autor> onSuccess;
	private final BiConsumer<AutorDto, AutorErrorForm> onFail;

	RegistroDoAutorHandler(HttpServletRequest request, HttpServletResponse response, Validator validator, Consumer<Autor> onSuccess, BiConsumer<AutorDto, AutorErrorForm> onFail) {
		this.request = request;
		this.response = response;
		this.validator = validator;
		this.onSuccess = onSuccess;
		this.onFail = onFail;
	}

	public void execute() throws ServletException, IOException {
		AutorDto autorDto = new AutorDto(request);
				
		Optional<AutorErrorForm> autorErroFormOptional = validator.valida(autorDto);

		if (autorErroFormOptional.isPresent()) {			
			onFail.accept(autorDto, autorErroFormOptional.get());			
			
			request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
			
		} else {
			onSuccess.accept(autorDto.toModel());

			response.sendRedirect("/autores");
		}
	}

}
