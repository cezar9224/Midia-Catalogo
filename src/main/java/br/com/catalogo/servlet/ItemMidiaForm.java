package br.com.catalogo.servlet;

import br.com.catalogo.model.ItemMidia;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;

public final class ItemMidiaForm {

    private final String titulo;
    private final String autorDiretor;
    private final String anoLancamento;
    private final String genero;
    private final String sinopse;
    private final String tipoMidia;

    public ItemMidiaForm(String titulo, String autorDiretor, String anoLancamento,
                         String genero, String sinopse, String tipoMidia) {
        this.titulo = titulo;
        this.autorDiretor = autorDiretor;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.sinopse = sinopse;
        this.tipoMidia = tipoMidia;
    }

    public static ItemMidiaForm fromRequest(HttpServletRequest request) {
        return new ItemMidiaForm(
                request.getParameter("titulo"),
                request.getParameter("autorDiretor"),
                request.getParameter("anoLancamento"),
                request.getParameter("genero"),
                request.getParameter("sinopse"),
                request.getParameter("tipoMidia"));
    }

    public static ItemMidiaForm fromItem(ItemMidia item) {
        return new ItemMidiaForm(
                item.getTitulo(),
                item.getAutorDiretor(),
                Integer.toString(item.getAnoLancamento()),
                item.getGenero(),
                item.getSinopse(),
                item.getTipoMidia());
    }

    public static ItemMidiaForm vazio() {
        return new ItemMidiaForm("", "", "", "", "", "");
    }

    public String validarDados() {
        if (titulo == null || titulo.isBlank()) {
            return "O título é obrigatório.";
        }
        if (tipoMidia == null || tipoMidia.isBlank()) {
            return "O tipo de mídia é obrigatório.";
        }
        if (anoLancamento == null || anoLancamento.isBlank()) {
            return "O ano de lançamento é obrigatório.";
        }

        int ano;
        try {
            ano = Integer.parseInt(anoLancamento.trim());
        } catch (NumberFormatException e) {
            return "O ano deve conter somente números.";
        }

        int anoAtual = LocalDate.now().getYear();
        if (ano < 0 || ano > anoAtual + 5) {
            return "O ano informado parece inválido.";
        }

        return null;
    }

    public ItemMidia toItem(int id) {
        return new ItemMidia(
                id,
                limpar(titulo),
                limpar(autorDiretor),
                Integer.parseInt(anoLancamento.trim()),
                limpar(genero),
                limpar(sinopse),
                limpar(tipoMidia));
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutorDiretor() {
        return autorDiretor;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    private String limpar(String valor) {
        return valor == null ? "" : valor.trim();
    }
}