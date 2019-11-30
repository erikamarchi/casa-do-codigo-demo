package br.com.caelum.casadocodigo.servlet.autor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.servlet.PathResolver;

@WebServlet(
		urlPatterns = "/autores/new"
)
public class ExibicaoDoAutorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(PathResolver.resolveName("autor/form")).forward(request, response);
	}

}
