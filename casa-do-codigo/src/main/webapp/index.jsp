<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="casadocodigo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<casadocodigo:template>
	<jsp:body>
		<h2 class="text-center">Seja bem-vindo :D</h2>
		<div class="container-fluid">
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">Autores</h5>
					<p class="card-text">Mostra todos os autores da casa do código</p>
					<a href="/autores" class="btn btn-primary">Visualizar</a>
				</div>
			</div>
		</div>	
		
	</jsp:body>	
</casadocodigo:template>
