package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.service.AutorService;
import br.com.caelum.casadocodigo.servlet.PathResolver;
import br.com.caelum.casadocodigo.servlet.autor.AutorErrorForm.Validator;

@WebServlet({ "/autores", "/autores/" })
public class AutorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		AutorService autorService = new AutorService(new AutorDao(connection));
		
		List<Autor> autores = autorService.buscaAutores();
		request.setAttribute("autores", autores);

		request.getRequestDispatcher(PathResolver.resolveName("autor/lista")).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		AutorService autorService = new AutorService(new AutorDao(connection));
		Validator validator = new AutorErrorForm.Validator(autorService);
		
		new RegistroDoAutorHandler(request, response, validator, 
				(autor) -> autorService.adiciona(autor),//onSucess
				(autorDto, autorErrorForm) -> {//onFail
					request.setAttribute("autor", autorDto);
					request.setAttribute("autorError", autorErrorForm);
				}).execute();
	}

}
