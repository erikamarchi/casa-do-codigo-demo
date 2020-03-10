package br.com.caelum.casadocodigo.servlet.livro;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.dao.CategoriaDao;
import br.com.caelum.casadocodigo.servlet.PathResolver;

@WebServlet(
		urlPatterns = "/livro/new"
)
public class ExibicaoDoLivroController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);
		AutorDao autorDao = new AutorDao(connection);
		
		request.setAttribute("categorias", categoriaDao.getLista());
		request.setAttribute("autores", autorDao.getLista());
		
		request.getRequestDispatcher(PathResolver.resolveName("livro/form")).forward(request, response);
	}
	
}
