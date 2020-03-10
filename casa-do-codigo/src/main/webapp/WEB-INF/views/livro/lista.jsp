<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<casadocodigo:template>
	<jsp:body>	
	
		<div class="col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
			<h3>Livros</h3>
	
			<table class="table table-hover ">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Categoria</th>
						<th>Autor</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="livro" items="${livros}">
						<tr id="livro_${livro.id}">
							<td>${livro.titulo}</td>
							<td>${livro.nomeDaCategoria}</td>
							<td>${livro.nomeDoAutor}</td>
							<td>
								<a href="/livros/${livro.id}" class="btn btn-primary">Editar</a>
								<a onclick="excluir(${livro.id})" class="btn btn-danger">Excluir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="justify-content-center	">
				<a href="/livro/new" class="btn btn-block btn-success">Novo</a>
			</div>
		</div>
		<script>
	            function excluir(id) {
	                var url = window.location.href;
	                $.ajax({
	                    url:"/livros/" + id,
	                    method: 'DELETE',
	                    success: function (result) {                        
	                        $("#livro_"+id).remove();
	                    }
	                });
	            }
        </script>
</jsp:body>	
</casadocodigo:template>