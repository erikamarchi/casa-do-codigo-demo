package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.model.Autor;
import br.com.caelum.casadocodigo.servlet.PathResolver;

@WebServlet(urlPatterns = "/autores/*")
public class AutorComIdController extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);

		PathResolver.getIdFrom(request).flatMap(autorDao::getAutor).ifPresent(autorDao::exclui);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new RegistroDoAutorHandler(request, response, (autorDao, autor) -> autorDao.atualiza(autor)).execute();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);
		PathResolver.getIdFrom(request).flatMap(autorDao::getAutor)
				.ifPresent(autor -> request.setAttribute("autor", autor));

		request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
	}

}
