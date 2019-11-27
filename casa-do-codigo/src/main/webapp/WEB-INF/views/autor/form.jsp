<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<casadocodigo:template>
<jsp:body>
	<div class=" col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
		<c:if test="${autor == null}">
			<form action="/autores" method="post" class="justify-content-center needs-validation" novalidate>
		</c:if>
		<c:if test="${autor != null}">
			<form action="/autores/${autor.id}" method="post" class="justify-content-center needs-validation" novalidate>
		</c:if>
		<div class="form-group text-left">
			<label for="Nome">Nome:</label> 
			<input id="nome" type="text" name="nome" class="form-control" required value="${autor.nome}">
			<div class="invalid-feedback">
		        Ei, não esqueça o nome do autor :D
		    </div>
		</div>

		<div class="form-group text-left">
			<label for="resumo">Resumo:</label>
			<textarea class="form-control rounded-0" id="resumo" rows="15" name="resumo" required>${autor.resumo}</textarea>
			<div class="invalid-feedback">
		        Você precisa nos contar um pouquinho sobre o autor, prometo que vai ser rápidinho ;-)
		    </div>
		</div>
		<c:if test="${autor == null}">
			<button class="btn btn-primary" type="submit">Criar autor</button>
		</c:if>
		<c:if test="${autor != null}">
			<button class="btn btn-primary" type="submit">Alterar autor</button>
		</c:if>
		<a href="/autores" class="btn btn-warning">Cancelar</a>
		</form>
	</div>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();
	</script>
</jsp:body>	
</casadocodigo:template>