<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<casadocodigo:template>
<jsp:body>
	<div class=" col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
		<form action='/categorias/${categoria.id == null ? "" : String.valueOf(categoria.id) }' method="post" class="justify-content-center" >
			<div class="form-group text-left">
				<label for="Nome">Nome:</label>
				
				<input id="nome" type="text" name="nome" class='form-control ${categoriaError == null ? "" : "is-invalid"}' value="${categoria.nome}">
				<div class="invalid-feedback">
			        ${categoriaError}
			    </div>
			</div>	
			
			<button class="btn btn-primary" type="submit">${categoria.id == null ? "Criar categoria" : "Alterar categoria" } </button>
			<a href="/categorias" class="btn btn-warning">Cancelar</a>
		</form>
	</div>

</jsp:body>	
</casadocodigo:template>