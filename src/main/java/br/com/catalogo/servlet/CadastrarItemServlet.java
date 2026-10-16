package br.com.catalogo.servlet;

import br.com.catalogo.dao.ItemMidiaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cadastrar")
public class CadastrarItemServlet extends HttpServlet {

    private final ItemMidiaDAO itemMidiaDAO = new ItemMidiaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("form", ItemMidiaForm.vazio());
        request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemMidiaForm form = ItemMidiaForm.fromRequest(request);
        String mensagem = form.validarDados();

        if (mensagem != null) {
            request.setAttribute("form", form);
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
            return;
        }

        try {
            itemMidiaDAO.inserir(form.toItem(0));

            request.setAttribute("form", ItemMidiaForm.vazio());
            request.setAttribute("mensagem", "Mídia cadastrada com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        } catch (SQLException e) {
            getServletContext().log("Falha ao cadastrar mídia no banco de dados.", e);
            request.setAttribute("form", form);
            request.setAttribute("mensagem", "Não foi possível salvar a mídia. Verifique o banco de dados.");
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
        }
    }
}
