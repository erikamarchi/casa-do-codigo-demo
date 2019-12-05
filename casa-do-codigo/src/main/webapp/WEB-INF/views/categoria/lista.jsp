<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<casadocodigo:template>
	<jsp:body>	
	
		<div class="col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
			<h3>Categorias</h3>
	
			<table class="table table-hover ">
				<thead>
					<tr>
						<th>Nome</th>						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="categoria" items="${categorias}">
						<tr id="categoria_${categoria.id}">
							<td>${categoria.nome}</td>							
							<td><a href="/categorias/${categoria.id}" class="btn btn-primary">Editar</a>
							<td><a onclick="excluir(${categoria.id})" class="btn btn-danger">Excluir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="justify-content-center	">
				<a href="/categoria/new" class="btn btn-block btn-success">Novo</a>
			</div>
		</div>
		<script>
	            function excluir(id) {
	                var url = window.location.href;
	                $.ajax({
	                    url:"/categorias/" + id,
	                    method: 'DELETE',
	                    success: function (result) {                        
	                        $("#categoria_"+id).remove();
	                    }
	                });
	            }
        </script>
</jsp:body>	
</casadocodigo:template>