<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autores</title>
<link type="text/css" href="<c:url	value="/assets/css/bootstrap.css"/>"
	rel="stylesheet" />
	<script type="text/javascript" src="/assets/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="/assets/js/bootstrap.min.js"></script>
</head>
<body>
	<div class=" col-md-6 col-md-offset-3">
		<h3>Autores</h3>

		<table class="table table-hover ">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Resumo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="autor" items="${autores}">
					<tr id="autor_${autor.id}">
						<td>${autor.nome}</td>
						<td>${autor.resumo}</td>
						<td><a href="/autores/form?id=${autor.id}" class="btn btn-primary">Editar</a>
						<td><a onclick="excluir(${autor.id})" class="btn btn-danger">Excluir</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-md-6 col-md-offset-3">
			<a href="/autores/form" class="btn btn-block btn-info">Novo</a>
		</div>
	</div>
	<script>
            function excluir(id) {
                var url = window.location.href;
                $.ajax({
                    url:"/autores/autor?id=" + id,
                    type: 'DELETE',
                    success: function (result) {                        
                        $("#autor_"+id).remove();
                    }
                });
            }
        </script>
</body>
</html>