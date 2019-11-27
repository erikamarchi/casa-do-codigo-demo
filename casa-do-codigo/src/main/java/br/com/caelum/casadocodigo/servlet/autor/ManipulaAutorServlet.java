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
import br.com.caelum.casadocodigo.servlet.PathResolver;

@WebServlet("/autores")
public class ManipulaAutorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);
		List<Autor> autores = autorDao.getLista();

		request.setAttribute("autores", autores);

		request.getRequestDispatcher(PathResolver.resolveName("autor/lista")).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");

		AutorDao autorDao = new AutorDao(connection);

		autorDao.adiciona(AutorMapper.mapper(request));

		response.sendRedirect("/autores");
	}

}
