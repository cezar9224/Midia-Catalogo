<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Mídia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Cadastrar nova mídia</h1>
        <div class="actions">
            <a class="button secondary" href="${pageContext.request.contextPath}/listar">Voltar para listagem</a>
        </div>
    </header>

    <c:if test="${not empty mensagem}">
        <div class="alert ${tipoMensagem == 'erro' ? 'error' : 'success'}">
            ${mensagem}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/cadastrar" method="post">
        <div class="form-grid">
            <div class="form-group">
                <label for="titulo">Título</label>
                <input id="titulo" name="titulo" type="text" value="${form.titulo}" required>
            </div>

            <div class="form-group">
                <label for="tipoMidia">Tipo de mídia</label>
                <select id="tipoMidia" name="tipoMidia" required>
                    <option value="">Selecione</option>
                    <option value="Livro" ${form.tipoMidia == 'Livro' ? 'selected' : ''}>Livro</option>
                    <option value="Filme" ${form.tipoMidia == 'Filme' ? 'selected' : ''}>Filme</option>
                    <option value="Série" ${form.tipoMidia == 'Série' ? 'selected' : ''}>Série</option>
                </select>
            </div>

            <div class="form-group">
                <label for="autorDiretor">Autor/Diretor</label>
                <input id="autorDiretor" name="autorDiretor" type="text" value="${form.autorDiretor}">
            </div>

            <div class="form-group">
                <label for="anoLancamento">Ano de lançamento</label>
                <input id="anoLancamento" name="anoLancamento" type="number" value="${form.anoLancamento}" min="0" max="2100">
            </div>

            <div class="form-group full">
                <label for="genero">Gênero</label>
                <input id="genero" name="genero" type="text" value="${form.genero}">
            </div>

            <div class="form-group full">
                <label for="sinopse">Sinopse</label>
                <textarea id="sinopse" name="sinopse" rows="6">${form.sinopse}</textarea>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit">Salvar</button>
            <a class="button secondary" href="${pageContext.request.contextPath}/listar">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>
