package br.com.caelum.casadocodigo.servlet.categoria;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.CategoriaDao;
import br.com.caelum.casadocodigo.model.Categoria;
import br.com.caelum.casadocodigo.servlet.PathResolver;

@WebServlet(urlPatterns = "/categorias/*")
public class CategoriaComIdController extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);

		PathResolver.getIdFrom(request).flatMap(categoriaDao::getCategoria).ifPresent(categoriaDao::exclui);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);

		CategoriaDto categoriaDto = new CategoriaDto(request);
		
		Optional<String> erroNome = CategoriaValidator.valida(categoriaDto, categoriaDao);

		if (erroNome.isPresent()) {			
			request.setAttribute("categoria", categoriaDto);
			request.setAttribute("categoriaError", erroNome.get());

			request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
		} else {			
			categoriaDao.atualiza(categoriaDto.toModel());

			response.sendRedirect("/categorias");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);

		Long idAtual = PathResolver.getIdFrom(request).orElse(null);
		if (idAtual != null) {
			Optional<Categoria> categoria = categoriaDao.getCategoria(idAtual);
			categoria.ifPresent(c -> 
				
				request.setAttribute("categoria", c)
			);

		}

		request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
	}

}
