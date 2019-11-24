package br.com.caelum.casadocodigo.servlet;

import javax.servlet.http.HttpServletRequest;

public class QueryParametersResolve {

	public static Long getParametroId(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(id == null) return (long) 0;
		
		return Long.parseLong(id);
	}
}
