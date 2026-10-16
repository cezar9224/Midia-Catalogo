package br.com.catalogo.servlet;

import br.com.catalogo.dao.ItemMidiaDAO;
import br.com.catalogo.model.ItemMidia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar")
public class ListarItensServlet extends HttpServlet {

    private final ItemMidiaDAO itemMidiaDAO = new ItemMidiaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ItemMidia> itens = itemMidiaDAO.listarTodos();
            request.setAttribute("itens", itens);
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "Erro ao listar os itens. Tente novamente.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        }
    }
}
