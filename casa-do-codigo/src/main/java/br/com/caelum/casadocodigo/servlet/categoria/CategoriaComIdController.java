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
import br.com.caelum.casadocodigo.servlet.categoria.CategoriaError.Validator;
import br.com.caelum.casadocodigo.validacao.GerenciadorDeCategoria;

@WebServlet(urlPatterns = "/categorias/*")
public class CategoriaComIdController extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);

		PathResolver.getIdFrom(request).flatMap(categoriaDao::getCategoria)
				.ifPresent(categoria -> categoriaDao.exclui(categoria.getId()));
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
				categoriaDao.atualiza(categoriaDto.toModel());

				response.sendRedirect("/categorias");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);

		Long idAtual = PathResolver.getIdFrom(request).orElse(null);
		if (idAtual != null) {
			Optional<Categoria> categoriaPossivel = categoriaDao.getCategoria(idAtual);
			categoriaPossivel.ifPresent(categoria -> request.setAttribute("categoria", categoria));

		}

		request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
	}

}
