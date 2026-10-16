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

@WebServlet("/buscar")
public class BuscarItemServlet extends HttpServlet {

    private final ItemMidiaDAO itemMidiaDAO = new ItemMidiaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String termo = request.getParameter("termo");

        if (termo == null) {
            termo = "";
        }

        try {
            List<ItemMidia> itens = itemMidiaDAO.buscar(termo);
            request.setAttribute("itens", itens);
            request.setAttribute("termo", termo);
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "Erro ao realizar a busca.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        }
    }
}
