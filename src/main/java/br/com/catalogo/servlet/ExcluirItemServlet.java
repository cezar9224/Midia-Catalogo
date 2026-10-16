package br.com.catalogo.servlet;

import br.com.catalogo.dao.ItemMidiaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/excluir")
public class ExcluirItemServlet extends HttpServlet {

    private final ItemMidiaDAO itemMidiaDAO = new ItemMidiaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTexto = request.getParameter("id");

        if (idTexto == null || idTexto.isBlank()) {
            response.sendRedirect("listar");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            itemMidiaDAO.excluir(id);
            response.sendRedirect("listar");
        } catch (NumberFormatException e) {
            response.sendRedirect("listar");
        } catch (SQLException e) {
            request.setAttribute("mensagem", "Erro ao excluir a mídia.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        }
    }
}
