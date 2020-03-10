package br.com.caelum.casadocodigo.servlet.livro;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.dao.CategoriaDao;
import br.com.caelum.casadocodigo.dao.LivroDao;
import br.com.caelum.casadocodigo.model.Livro;
import br.com.caelum.casadocodigo.servlet.PathResolver;
import br.com.caelum.casadocodigo.servlet.livro.LivroError.Validator;
import br.com.caelum.casadocodigo.validacao.GerenciadorDeLivro;

@WebServlet(urlPatterns = "/livros/*")
public class LivroComIdController extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		LivroDao livroDao = new LivroDao(connection);

		PathResolver.getIdFrom(request).flatMap(livroDao::getLivro)
				.ifPresent(livro -> livroDao.exclui(livro.getId()));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LivroDto livroDto = new LivroDto(request);
		Validator validator = new Validator(livroDto);

		Optional<LivroError> livroErroOptional = validator.validaForm();

		if (livroErroOptional.isPresent()) {			
			request.setAttribute("livro", livroDto);
			request.setAttribute("livroError", livroErroOptional.get());

			request.getRequestDispatcher(PathResolver.resolveName("livro/form")).forward(request, response);
		} else {
			Connection connection = (Connection) request.getAttribute("conexao");
			LivroDao livroDao = new LivroDao(connection);

			GerenciadorDeLivro gerenciadorDeLivro = new GerenciadorDeLivro(livroDao);
			livroErroOptional = validator.validaRegrasDeLivro(gerenciadorDeLivro);
			
			if (livroErroOptional.isPresent()) {
				request.setAttribute("livro", livroDto);
				request.setAttribute("livroError", livroErroOptional.get());

				request.getRequestDispatcher(PathResolver.resolveName("livro/form")).forward(request, response);
			} else {
				livroDao.atualiza(livroDto.toModel());

				response.sendRedirect("/livros");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		LivroDao livroDao = new LivroDao(connection);
		CategoriaDao categoriaDao = new CategoriaDao(connection);
		AutorDao autorDao = new AutorDao(connection);
		
		request.setAttribute("categorias", categoriaDao.getLista());
		request.setAttribute("autores", autorDao.getLista());

		Long idAtual = PathResolver.getIdFrom(request).orElse(null);
		if (idAtual != null) {
			Optional<Livro> livroPossivel = livroDao.getLivro(idAtual);
			livroPossivel.ifPresent(livro -> request.setAttribute("livro", livro));

		}

		request.getRequestDispatcher(PathResolver.resolveName("livro/form")).forward(request, response);
	}

}
