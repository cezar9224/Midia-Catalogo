<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Detalhes da Mídia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Detalhes da mídia</h1>
        <div class="actions">
            <a class="button secondary" href="${pageContext.request.contextPath}/listar">Voltar para listagem</a>
            <a class="button" href="${pageContext.request.contextPath}/editar?id=${item.id}">Editar</a>
        </div>
    </header>

    <c:if test="${not empty item}">
        <div class="details">
            <div>
                <div class="label">Título</div>
                <div class="value">${item.titulo}</div>
            </div>
            <div>
                <div class="label">Tipo</div>
                <div class="value">${item.tipoMidia}</div>
            </div>
            <div>
                <div class="label">Autor/Diretor</div>
                <div class="value">${item.autorDiretor}</div>
            </div>
            <div>
                <div class="label">Ano</div>
                <div class="value">${item.anoLancamento}</div>
            </div>
            <div>
                <div class="label">Gênero</div>
                <div class="value">${item.genero}</div>
            </div>
            <div class="full">
                <div class="label">Sinopse</div>
                <div class="value">${item.sinopse}</div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
