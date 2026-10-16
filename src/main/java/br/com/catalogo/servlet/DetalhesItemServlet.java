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

@WebServlet("/detalhes")
public class DetalhesItemServlet extends HttpServlet {

    private final ItemMidiaDAO itemMidiaDAO = new ItemMidiaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTexto = request.getParameter("id");

        if (idTexto == null || idTexto.isBlank()) {
            request.setAttribute("mensagem", "Identificador inválido.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            ItemMidia item = itemMidiaDAO.buscarPorId(id);

            if (item == null) {
                request.setAttribute("mensagem", "Mídia não encontrada.");
                request.setAttribute("tipoMensagem", "erro");
                request.getRequestDispatcher("/lista.jsp").forward(request, response);
                return;
            }

            request.setAttribute("item", item);
            request.getRequestDispatcher("/detalhes.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "ID inválido.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "Erro ao carregar os detalhes da mídia.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        }
    }
}
