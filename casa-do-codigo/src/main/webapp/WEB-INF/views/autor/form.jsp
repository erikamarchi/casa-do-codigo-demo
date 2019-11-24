<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Novo autor</title>
<link type="text/css" href="<c:url	value="/assets/css/bootstrap.css"/>"
	rel="stylesheet" />
</head>
<body>
        <div class=" col-md-6 col-md-offset-3">	
            <form action="/autores/adiciona" method="post">
                <div class="form-group">
                    <label for="Nome">Nome:</label>
                    <input id="nome" type="text" name="nome" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="resumo">Resumo:</label>
                    <textarea class="form-control rounded-0" id="resumo" rows="15" name="resumo" required></textarea>
                </div>

                <button class="btn btn-primary" type="submit">Criar autor</button>
            </form>
        </div>
    </body>
</html>