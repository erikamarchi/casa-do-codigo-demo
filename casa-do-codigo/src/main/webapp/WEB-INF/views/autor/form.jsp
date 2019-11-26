<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<body>

	<ul class="nav ">
		<li class="nav-item"><a class="nav-link active" href="/">Home</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="/autores">Autores</a>
		</li>
	</ul>

	<div
		class=" col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
		<c:if test="${autor == null}">
			<form action="/autores" method="post" class="justify-content-center needs-validation" novalidate>
		</c:if>
		<c:if test="${autor != null}">
			<form action="/autores/${autor.id}" method="post" class="justify-content-center needs-validation" novalidate>
		</c:if>
		<div class="form-group">
			<label for="Nome">Nome:</label> 
			<input id="nome" type="text" name="nome" class="form-control" required value="${autor.nome}">
			<div class="invalid-feedback">
		        Ei, não esqueça o nome do autor :D
		    </div>
		</div>

		<div class="form-group">
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
</body>
</html>