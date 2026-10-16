<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Mídias</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background: #f5f7fb;
            color: #1f2937;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
        }
        h1 {
            margin-bottom: 16px;
        }
        .actions {
            margin-top: 24px;
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }
        .button {
            display: inline-block;
            text-decoration: none;
            background: #2563eb;
            color: white;
            padding: 10px 18px;
            border-radius: 8px;
            font-weight: bold;
        }
        .button.secondary {
            background: #475569;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>CATÁLOGO DE MÍDIAS</h1>
        <p>Aplicação web para cadastrar, listar, editar, excluir e buscar livros, filmes e séries.</p>

        <div class="actions">
            <a class="button" href="listar">Listar itens</a>
            <a class="button secondary" href="cadastro.jsp">Cadastrar nova mídia</a>
        </div>
    </div>
</body>
</html>
