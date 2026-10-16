# Catálogo de Mídias

Aplicação web acadêmica em Java para cadastrar, listar, buscar, editar e excluir livros, filmes e séries.

## Tecnologias

- Java 21
- Maven
- Jakarta Servlets, JSP e JSTL
- MySQL 8
- Apache Tomcat 10.1

## Banco de dados

Execute no MySQL:

```sql
CREATE DATABASE IF NOT EXISTS catalogo_midia;
USE catalogo_midia;

CREATE TABLE IF NOT EXISTS item_midia (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor_diretor VARCHAR(255),
    ano_lancamento INT,
    genero VARCHAR(100),
    sinopse TEXT,
    tipo_midia VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## Configuração local

1. Copie `src/main/resources/db.properties.example` para `src/main/resources/db.properties`.
2. Edite `db.properties` com o usuário e a senha do seu MySQL. Esse arquivo é ignorado pelo Git; não publique credenciais.
3. Gere o arquivo WAR:

```powershell
mvn clean package
```

4. Copie `target/catalogo-midia.war` para a pasta `webapps` do Tomcat 10.1 e inicie o Tomcat.
5. Acesse `http://localhost:8080/catalogo-midia/`.

## Aviso de segurança

Projeto acadêmico para execução local. Não disponibilize na Internet sem implementar autenticação e autorização, proteção CSRF, escape seguro das saídas JSP, uso de um usuário MySQL com privilégios mínimos e configuração de rede/TLS adequada. O usuário `root` não deve ser usado em produção.