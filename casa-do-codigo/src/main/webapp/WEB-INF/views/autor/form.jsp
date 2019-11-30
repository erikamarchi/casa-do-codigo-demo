<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<casadocodigo:template>
<jsp:body>
	<div class=" col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
		<form action='/autores/${autor.id == null ? "" : String.valueOf(autor.id) }' method="post" class="justify-content-center" >
			<div class="form-group text-left">
				<label for="Nome">Nome:</label>
				
				<input id="nome" type="text" name="nome" class='form-control ${autorError.erroNome == null ? "" : "is-invalid"}' value="${autor.nome}">
				<div class="invalid-feedback">
			        ${autorError.erroNome}
			    </div>
			</div>
	
			<div class="form-group text-left">
				<label for="resumo">Resumo:</label>
				<textarea class='form-control rounded-0 ${autorError.erroResumo == null ? "" : "is-invalid"}' id="resumo" rows="15" name="resumo" >${autor.resumo}</textarea>
				<div class="invalid-feedback">
			        ${autorError.erroResumo}
			    </div>
			</div>
			<button class="btn btn-primary" type="submit">${autor.id == null ? "Criar autor" : "Alterar autor" } </button>
			<a href="/autores" class="btn btn-warning">Cancelar</a>
		</form>
	</div>

</jsp:body>	
</casadocodigo:template>