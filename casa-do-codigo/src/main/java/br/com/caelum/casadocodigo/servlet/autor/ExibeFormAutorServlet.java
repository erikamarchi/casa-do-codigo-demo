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
import br.com.caelum.casadocodigo.servlet.PathResolver;
import br.com.caelum.casadocodigo.servlet.QueryParametersResolve;

@WebServlet(
		urlPatterns = "/autores/form", 
		initParams = { 
				@WebInitParam(name = "id", value = "{id}") 
		}
)
public class ExibeFormAutorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		carregaAutor(request);

		request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
	}

	private void carregaAutor(HttpServletRequest request) {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);
		Autor autor = autorDao.getAutor(QueryParametersResolve.getParametroId(request));

		request.setAttribute("autor", autor);
	}

}
