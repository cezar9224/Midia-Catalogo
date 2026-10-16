package br.com.catalogo.servlet;

import br.com.catalogo.model.ItemMidia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemMidiaFormTest {

    @Test
    void deveInformarQuandoAnoNaoForNumerico() {
        ItemMidiaForm form = new ItemMidiaForm(
                "Título", "Autor", "abc", "Gênero", "Sinopse", "Livro");

        assertEquals("O ano deve conter somente números.", form.validarDados());
    }

    @Test
    void deveValidarEConverterCamposRemovendoEspacosExcedentes() {
        ItemMidiaForm form = new ItemMidiaForm(
                "  Duna  ", "  Frank Herbert  ", " 1965 ", "  Ficção  ", "  Sinopse  ", " Livro ");

        assertNull(form.validarDados());

        ItemMidia item = form.toItem(7);
        assertEquals(7, item.getId());
        assertEquals("Duna", item.getTitulo());
        assertEquals("Frank Herbert", item.getAutorDiretor());
        assertEquals(1965, item.getAnoLancamento());
        assertEquals("Ficção", item.getGenero());
        assertEquals("Sinopse", item.getSinopse());
        assertEquals("Livro", item.getTipoMidia());
    }
}