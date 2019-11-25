package br.com.caelum.casadocodigo.servlet;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public class PathResolver {

	public static String resolveName(String name) {
		return String.format("/WEB-INF/views/%s.jsp", name);
	}
	
	public static Optional<Long> getIdFrom(HttpServletRequest request) {
		// /autores/1
		return Optional.
			ofNullable(request.getPathInfo()).
			map(uri -> uri.split("/")).
			map(Arrays::asList).
			filter(segmentosDaUri -> segmentosDaUri.size() > 1).
			map(segmentosDaUri -> segmentosDaUri.get(1)).
			map(Long::parseLong);
	}
}
