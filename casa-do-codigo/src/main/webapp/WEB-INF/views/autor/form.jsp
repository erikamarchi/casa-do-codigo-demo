<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Novo autor</title>
<link type="text/css" href="<c:url	value="/assets/css/bootstrap.css"/>"
	rel="stylesheet" />
</head>
<body>
	<div class=" col-md-6 col-md-offset-3">
		<c:if test="${autor == null}">
			<form action="/autores" method="post">
		</c:if>
		<c:if test="${autor != null}">
			<form action="/autores/autor?id=${autor.id}" method="put">
		</c:if>
		<div class="form-group">
			<label for="Nome">Nome:</label> 
			<input id="nome" type="text"
				name="nome" class="form-control" required value="${autor.nome}">
		</div>

		<div class="form-group">
			<label for="resumo">Resumo:</label>
			<textarea class="form-control rounded-0" id="resumo" rows="15"
				name="resumo" required>${autor.resumo}</textarea>
		</div>
		<c:if test="${autor == null}">
			<button class="btn btn-primary" type="submit">Criar autor</button>
		</c:if>
		<c:if test="${autor != null}">
			<button class="btn btn-primary" type="submit">Alterar autor</button>
		</c:if>
			<a href="/autores" class="btn btn-warning" >Cancelar</a>
		</form>
	</div>
</body>
</html>