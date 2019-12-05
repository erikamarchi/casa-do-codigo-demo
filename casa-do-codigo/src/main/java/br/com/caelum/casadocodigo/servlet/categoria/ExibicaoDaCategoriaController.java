package br.com.caelum.casadocodigo.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.servlet.PathResolver;

@WebServlet(
		urlPatterns = "/categoria/new"
)
public class ExibicaoDaCategoriaController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
	}
	
}
