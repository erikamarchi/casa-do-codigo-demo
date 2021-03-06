package br.com.caelum.casadocodigo.servlet.categoria;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.CategoriaDao;
import br.com.caelum.casadocodigo.model.Categoria;
import br.com.caelum.casadocodigo.servlet.PathResolver;
import br.com.caelum.casadocodigo.servlet.categoria.CategoriaError.Validator;
import br.com.caelum.casadocodigo.validacao.GerenciadorDeCategoria;

@WebServlet({ "/categorias", "/categorias/" })
public class CategoriaController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);

		List<Categoria> categorias = categoriaDao.getLista();
		request.setAttribute("categorias", categorias);

		request.getRequestDispatcher(PathResolver.resolveName("categoria/lista")).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoriaDto categoriaDto = new CategoriaDto(request);
		Validator validator = new Validator(categoriaDto);

		Optional<CategoriaError> categoriaErroOptional = validator.validaForm();

		if (categoriaErroOptional.isPresent()) {			
			request.setAttribute("categoria", categoriaDto);
			request.setAttribute("categoriaError", categoriaErroOptional.get());

			request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
		} else {
			Connection connection = (Connection) request.getAttribute("conexao");
			CategoriaDao categoriaDao = new CategoriaDao(connection);

			GerenciadorDeCategoria gerenciadorDeCategoria = new GerenciadorDeCategoria(categoriaDao);
			categoriaErroOptional = validator.validaRegrasDeCategoria(gerenciadorDeCategoria);
			
			if (categoriaErroOptional.isPresent()) {
				request.setAttribute("categoria", categoriaDto);
				request.setAttribute("categoriaError", categoriaErroOptional.get());

				request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
			} else {
				categoriaDao.adiciona(categoriaDto.toModel());

				response.sendRedirect("/categorias");
			}
		}		
		
	}
}
