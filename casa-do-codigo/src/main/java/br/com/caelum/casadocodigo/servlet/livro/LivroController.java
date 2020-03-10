package br.com.caelum.casadocodigo.servlet.livro;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
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

@WebServlet({ "/livros", "/livros/" })
public class LivroController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("conexao");
		LivroDao livroDao = new LivroDao(connection);

		List<Livro> livros = livroDao.getLista();
		request.setAttribute("livros", livros);

		request.getRequestDispatcher(PathResolver.resolveName("livro/lista")).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LivroDto livroDto = new LivroDto(request);
		Validator validator = new Validator(livroDto);
		
		Connection connection = (Connection) request.getAttribute("conexao");
		CategoriaDao categoriaDao = new CategoriaDao(connection);
		AutorDao autorDao = new AutorDao(connection);

		Optional<LivroError> livroErroOptional = validator.validaForm();

		if (livroErroOptional.isPresent()) {
			request.setAttribute("categorias", categoriaDao.getLista());
			request.setAttribute("autores", autorDao.getLista());
			request.setAttribute("livro", livroDto);
			request.setAttribute("livroError", livroErroOptional.get());

			request.getRequestDispatcher(PathResolver.resolveName("livro/form")).forward(request, response);
		} else {
			LivroDao livroDao = new LivroDao(connection);

			GerenciadorDeLivro gerenciadorDeLivro = new GerenciadorDeLivro(livroDao);
			livroErroOptional = validator.validaRegrasDeLivro(gerenciadorDeLivro);
			
			if (livroErroOptional.isPresent()) {
				request.setAttribute("categorias", categoriaDao.getLista());
				request.setAttribute("autores", autorDao.getLista());
				
				request.setAttribute("livro", livroDto);
				request.setAttribute("livroError", livroErroOptional.get());

				request.getRequestDispatcher(PathResolver.resolveName("livro/form")).forward(request, response);
			} else {
				livroDao.adiciona(livroDto.toModel());

				response.sendRedirect("/livros");
			}
		}		
		
	}
}
