package br.com.caelum.casadocodigo.servlet;

public class PathResolver {

	public static String resolveName(String name) {
		return String.format("/WEB-INF/views/%s.jsp", name);
	}

}
