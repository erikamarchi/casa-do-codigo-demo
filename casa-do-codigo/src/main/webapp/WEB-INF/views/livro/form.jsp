<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<casadocodigo:template>
<jsp:body>
	<div class=" col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
		<form action='/livros/${livro.id == null ? "" : String.valueOf(livro.id) }' method="post" class="justify-content-center" >
			<div class="form-group text-left">
				<label for="titulo">Titulo:</label>
				
				<input id="titulo" type="text" name="titulo" class='form-control ${livroError.erroTitulo == null ? "" : "is-invalid"}' value="${livro.titulo}">
				<div class="invalid-feedback">
			        ${livroError.erroTitulo}
			    </div>
			</div>
			
			<div class="form-group text-left">
				<label for="idDaCategoria">Categoria:</label>
				
				<select class='form-control ${livroError.erroCategoria == null ? "" : "is-invalid"}' id="idDaCategoria" name="idDaCategoria">
					<option></option>
					<c:forEach var="categoria" items="${categorias}">
						<option value="${categoria.id}" ${categoria.id == livro.idDaCategoria ? "selected" : ""}>${categoria.nome}</option>
					</c:forEach>
			    </select>
				<div class="invalid-feedback">
			        ${livroError.erroCategoria}
			    </div>
			</div>
			
			<div class="form-group text-left">
				<label for="preco">Preço:</label>

				<input id="preco" type="text" name="preco" class='form-control ${livroError.erroPreco == null ? "" : "is-invalid"}' value="${livro.precoFormatado}">
				<div class="invalid-feedback">
			        ${livroError.erroPreco}
			    </div>
			</div>
	
			<div class="form-group text-left">
				<label for="resumo">Resumo:</label>
				<textarea class='form-control rounded-0 ${livroError.erroResumo == null ? "" : "is-invalid"}' id="resumo" rows="15" name="resumo" >${livro.resumo}</textarea>
				<div class="invalid-feedback">
			        ${livroError.erroResumo}
			    </div>
			</div>

			<div class="form-group text-left">
				<label for="idDoAutor">Autor:</label>
				
				<select class='form-control ${livroError.erroAutor == null ? "" : "is-invalid"}' id="idDoAutor" name="idDoAutor">
					<option></option>
					<c:forEach var="autor" items="${autores}">
						<option value="${autor.id}" ${autor.id == livro.idDoAutor ? "selected" : ""}>${autor.nome}</option>
					</c:forEach>
			    </select>
				<div class="invalid-feedback">
			        ${livroError.erroAutor}
			    </div>
			</div>
			
			<div class="form-group text-left">
				<label for="numeroDePaginas">Número de páginas:</label>
				
				<input id="numeroDePaginas" type="text" name="numeroDePaginas" class='form-control ${livroError.erroNumeroDePaginas == null ? "" : "is-invalid"}' value="${livro.numeroDePaginas}">
				<div class="invalid-feedback">
			        ${livroError.erroNumeroDePaginas}
			    </div>
			</div>
			
			<div class="form-group text-left">
				<label for="isbn">ISBN:</label>
				
				<input id="isbn" type="text" name="isbn" class='form-control ${livroError.erroIsbn == null ? "" : "is-invalid"}' value="${livro.isbn}">
				<div class="invalid-feedback">
			        ${livroError.erroIsbn}
			    </div>
			</div>
			
			<button class="btn btn-primary" type="submit">${livro.id == null ? "Criar livro" : "Alterar livro" } </button>
			<a href="/livros" class="btn btn-warning">Cancelar</a>
		</form>
	</div>

</jsp:body>	
</casadocodigo:template>