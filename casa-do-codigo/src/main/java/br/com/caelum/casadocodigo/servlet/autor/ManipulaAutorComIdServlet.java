package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.QueryParametersResolve;

@WebServlet(
		urlPatterns = "/autores/autor", 
		initParams = { 
				@WebInitParam(name = "id", value = "{id}") 
		}
)
public class ManipulaAutorComIdServlet extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);

		Autor autor = autorDao.getAutor(QueryParametersResolve.getParametroId(request));
		autorDao.exclui(autor);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);
		
		Autor autor = AutorMapper.mapper(request);

		autorDao.atualiza(autor);

		System.out.println("vai pro autores");
		response.sendRedirect("/autores");
	}

}
