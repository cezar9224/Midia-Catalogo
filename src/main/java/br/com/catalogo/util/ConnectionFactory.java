package br.com.catalogo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPERTIES_FILE = "db.properties";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do MySQL não encontrado.", e);
        }

        Properties properties = loadProperties();
        String url = properties.getProperty("db.url", "jdbc:mysql://localhost:3306/catalogo_midia");
        String username = properties.getProperty("db.username", "root");
        String password = properties.getProperty("db.password", "");

        return DriverManager.getConnection(url, username, password);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível carregar as configurações do banco.", e);
        }

        return properties;
    }
}
