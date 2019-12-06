package br.com.caelum.casadocodigo.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.casadocodigo.servlet.PathResolver;

public class CategoriaErrorHandler {

	public static void onFail(HttpServletRequest request, HttpServletResponse response, CategoriaDto categoriaDto,
			CategoriaError categoriaError) throws ServletException, IOException {
		request.setAttribute("categoria", categoriaDto);
		request.setAttribute("categoriaError", categoriaError);

		request.getRequestDispatcher(PathResolver.resolveName("categoria/form")).forward(request, response);
	}
}
