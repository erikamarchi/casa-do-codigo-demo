package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.service.AutorService;
import br.com.caelum.casadocodigo.servlet.PathResolver;
import br.com.caelum.casadocodigo.servlet.autor.AutorErrorForm.Validator;

@WebServlet(urlPatterns = "/autores/*")
public class AutorComIdController extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		AutorService autorService = new AutorService (new AutorDao(connection));

		PathResolver.getIdFrom(request).flatMap(autorService::buscaAutor).ifPresent(autorService::exclui);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");				
		AutorService autorService = new AutorService(new AutorDao(connection));
		Validator validator = new AutorErrorForm.Validator(autorService);
		
		new RegistroDoAutorHandler(request, response, validator, 
				(autor) -> autorService.atualiza(autor), // onSucess
				(autorDto, autorErrorForm) -> {// onFail
					request.setAttribute("autor", autorDto);
					request.setAttribute("autorError", autorErrorForm);
				}).execute();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		AutorService autorService = new AutorService(new AutorDao(connection));
		
		PathResolver.getIdFrom(request).flatMap(autorService::buscaAutor)
				.ifPresent(autor -> request.setAttribute("autor", autor));

		request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
	}

}
