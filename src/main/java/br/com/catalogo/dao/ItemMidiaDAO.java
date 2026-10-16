package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemMidiaDAO {

    public void inserir(ItemMidia item) throws SQLException {
        String sql = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getTitulo());
            statement.setString(2, item.getAutorDiretor());
            statement.setInt(3, item.getAnoLancamento());
            statement.setString(4, item.getGenero());
            statement.setString(5, item.getSinopse());
            statement.setString(6, item.getTipoMidia());
            statement.executeUpdate();
        }
    }

    public List<ItemMidia> listarTodos() throws SQLException {
        String sql = "SELECT * FROM item_midia ORDER BY id DESC";
        List<ItemMidia> itens = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                itens.add(mapearItem(resultSet));
            }
        }

        return itens;
    }

    public ItemMidia buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM item_midia WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearItem(resultSet);
                }
            }
        }

        return null;
    }

    public List<ItemMidia> buscar(String termo) throws SQLException {
        String sql = "SELECT * FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ? ORDER BY id DESC";
        List<ItemMidia> itens = new ArrayList<>();
        String valor = "%" + termo.trim() + "%";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, valor);
            statement.setString(2, valor);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    itens.add(mapearItem(resultSet));
                }
            }
        }

        return itens;
    }

    public void atualizar(ItemMidia item) throws SQLException {
        String sql = "UPDATE item_midia SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, genero = ?, sinopse = ?, tipo_midia = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getTitulo());
            statement.setString(2, item.getAutorDiretor());
            statement.setInt(3, item.getAnoLancamento());
            statement.setString(4, item.getGenero());
            statement.setString(5, item.getSinopse());
            statement.setString(6, item.getTipoMidia());
            statement.setInt(7, item.getId());
            statement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM item_midia WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private ItemMidia mapearItem(ResultSet resultSet) throws SQLException {
        ItemMidia item = new ItemMidia();
        item.setId(resultSet.getInt("id"));
        item.setTitulo(resultSet.getString("titulo"));
        item.setAutorDiretor(resultSet.getString("autor_diretor"));
        item.setAnoLancamento(resultSet.getInt("ano_lancamento"));
        item.setGenero(resultSet.getString("genero"));
        item.setSinopse(resultSet.getString("sinopse"));
        item.setTipoMidia(resultSet.getString("tipo_midia"));
        return item;
    }
}
