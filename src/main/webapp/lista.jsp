<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Mídias</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Catálogo de Mídias</h1>
        <div class="actions">
            <a class="button" href="${pageContext.request.contextPath}/">Início</a>
            <a class="button secondary" href="${pageContext.request.contextPath}/cadastrar">Cadastrar nova mídia</a>
        </div>
    </header>

    <c:if test="${not empty mensagem}">
        <div class="alert ${tipoMensagem == 'erro' ? 'error' : 'success'}">
            ${mensagem}
        </div>
    </c:if>

    <form class="search-form" action="${pageContext.request.contextPath}/buscar" method="get">
        <input type="text" name="termo" value="${termo}" placeholder="Buscar por título ou autor/diretor">
        <button type="submit">Buscar</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>Título</th>
            <th>Tipo</th>
            <th>Autor/Diretor</th>
            <th>Ano</th>
            <th>Gênero</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty itens}">
                <c:forEach var="item" items="${itens}">
                    <tr>
                        <td>${item.titulo}</td>
                        <td>${item.tipoMidia}</td>
                        <td>${item.autorDiretor}</td>
                        <td>${item.anoLancamento}</td>
                        <td>${item.genero}</td>
                        <td>
                            <div class="row-actions">
                                <a class="button secondary" href="${pageContext.request.contextPath}/detalhes?id=${item.id}">Detalhes</a>
                                <a class="button" href="${pageContext.request.contextPath}/editar?id=${item.id}">Editar</a>
                                <a class="button danger" href="${pageContext.request.contextPath}/excluir?id=${item.id}" 
                                   onclick="return confirm('Deseja realmente excluir esta mídia?');">Excluir</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6">Nenhuma mídia encontrada.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
