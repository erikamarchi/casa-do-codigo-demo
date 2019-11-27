<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<casadocodigo:template>
	<jsp:body>	
	
		<div class="col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
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
							<td><a href="/autores/${autor.id}" class="btn btn-primary">Editar</a>
							<td><a onclick="excluir(${autor.id})" class="btn btn-danger">Excluir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="justify-content-center	">
				<a href="/autores/new" class="btn btn-block btn-success">Novo</a>
			</div>
		</div>
		<script>
	            function excluir(id) {
	                var url = window.location.href;
	                $.ajax({
	                    url:"/autores/" + id,
	                    method: 'DELETE',
	                    success: function (result) {                        
	                        $("#autor_"+id).remove();
	                    }
	                });
	            }
        </script>
</jsp:body>	
</casadocodigo:template>