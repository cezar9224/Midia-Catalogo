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

@WebServlet("/editar")
public class EditarItemServlet extends HttpServlet {

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
            request.setAttribute("form", ItemMidiaForm.fromItem(item));
            request.getRequestDispatcher("/editar.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "ID inválido.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "Erro ao carregar a mídia para edição.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ItemMidia item = itemMidiaDAO.buscarPorId(id);
            if (item == null) {
                response.sendRedirect("listar");
                return;
            }

            ItemMidiaForm form = ItemMidiaForm.fromRequest(request);
            String mensagem = form.validarDados();
            if (mensagem != null) {
                request.setAttribute("item", item);
                request.setAttribute("form", form);
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("tipoMensagem", "erro");
                request.getRequestDispatcher("/editar.jsp").forward(request, response);
                return;
            }

            itemMidiaDAO.atualizar(form.toItem(id));
            response.sendRedirect("listar");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Dados numéricos inválidos.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        } catch (SQLException e) {
            getServletContext().log("Falha ao atualizar mídia no banco de dados.", e);
            request.setAttribute("mensagem", "Erro ao atualizar a mídia.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/lista.jsp").forward(request, response);
        }
    }

}
